package utils;

import edu.stanford.nlp.ie.util.RelationTriple;
import edu.stanford.nlp.ling.CoreAnnotations;
import edu.stanford.nlp.ling.CoreLabel;
import edu.stanford.nlp.trees.TypedDependency;
import edu.stanford.nlp.util.CoreMap;
import edu.stanford.nlp.util.StringUtils;
import model.Scenario;

import java.util.*;
import java.util.regex.Pattern;

@SuppressWarnings("SuspiciousListRemoveInLoop")
public class MyParser {

    private HashSet<String> foundClassList;
    private HashSet<String> notFoundClassList;

    private HashSet<String> foundAttributeList;
    private HashSet<String> notFoundAttributeList;

    private HashSet<String> potentialFoundAttributeList;
    private HashSet<String> notPotentialFoundAttributeList;

    private HashSet<String> potentialRelationList;

    private HashSet<String> foundMethodList;
    private HashSet<String> notFoundMethodList;

    private HashSet<String> foundRelationshipList;
    private HashSet<String> notFoundRelationshipList;


    private int classCount;
    private int attrCount;
    private int methodCount;
    private int relationshipCount;
    private Scenario currentScenario;
    private StanfordCoreUtils stanfordCoreUtils;
    private WordNetUtils wordNetUtils;
    private SearchModel searchModel;
    private String userRole = "";
    private List<String> stopwordsList;
    private List<String> ignoreEntitiesList;
    private List<String> ignoreVerbsList;

    public MyParser() throws Exception {
        foundClassList = new HashSet<>();
        notFoundClassList = new HashSet<>();
        foundAttributeList = new HashSet<>();
        notFoundAttributeList = new HashSet<>();
        potentialFoundAttributeList = new HashSet<>();
        notPotentialFoundAttributeList = new HashSet<>();
        potentialRelationList = new HashSet<>();
        foundMethodList = new HashSet<>();
        notFoundMethodList = new HashSet<>();
        foundRelationshipList = new HashSet<>();
        notFoundRelationshipList = new HashSet<>();
        String[] stopwords = new String[]{"a", "as", "able", "about", "above", "according", "accordingly", "across", "actually", "after", "afterwards", "again", "against", "aint", "all", "allow", "allows", "almost", "alone", "along", "already", "also", "although", "always", "am", "among", "amongst", "an", "and", "another", "any", "anybody", "anyhow", "anyone", "anything", "anyway", "anyways", "anywhere", "apart", "appear", "appreciate", "appropriate", "are", "arent", "around", "as", "aside", "ask", "asking", "associated", "at", "availability", "away", "awfully", "be", "became", "because", "become", "becomes", "becoming", "been", "before", "beforehand", "behind", "being", "believe", "below", "beside", "besides", "best", "better", "between", "beyond", "both", "brief", "but", "by", "cmon", "cs", "came", "can", "cant", "cannot", "cant", "cause", "causes", "certain", "certainly", "changes", "clearly", "co", "com", "come", "comes", "concerning", "consequently", "consider", "considering", "contain", "containing", "contains", "corresponding", "could", "couldnt", "course", "currently", "definitely", "described", "despite", "did", "didnt", "different", "dim", "do", "does", "doesnt", "doing", "dont", "done", "down", "downwards", "during", "each", "edu", "eg", "eight", "either", "else", "elsewhere", "enough", "entirely", "especially", "et", "etc", "even", "ever", "every", "everybody", "everyone", "everything", "everywhere", "ex", "exactly", "example", "except", "far", "few", "ff", "fifth", "first", "five", "followed", "following", "follows", "for", "former", "formerly", "forth", "four", "from", "further", "furthermore", "get", "gets", "getting", "given", "gives", "go", "goes", "going", "gone", "got", "gotten", "greetings", "had", "hadnt", "happens", "hardly", "has", "hasnt", "have", "havent", "having", "he", "hes", "hello", "help", "hence", "her", "here", "heres", "hereafter", "hereby", "herein", "hereupon", "hers", "herself", "hi", "him", "himself", "his", "hither", "hopefully", "how", "howbeit", "however", "i", "id", "ill", "im", "ive", "ie", "if", "ignored", "immediate", "in", "inasmuch", "inc", "indeed", "indicate", "indicated", "indicates", "inner", "insofar", "instead", "into", "inward", "is", "isnt", "it", "itd", "itll", "its", "its", "itself", "just", "keep", "keeps", "kept", "know", "knows", "known", "last", "lately", "later", "latter", "latterly", "least", "less", "lest", "let", "lets", "like", "liked", "likely", "little", "look", "looking", "looks", "ltd", "mainly", "many", "may", "maybe", "me", "mean", "meanwhile", "merely", "might", "more", "moreover", "most", "mostly", "much", "must", "my", "myself", "name", "namely", "nd", "near", "nearly", "necessary", "need", "needs", "neither", "never", "nevertheless", "new", "next", "nice", "nine", "no", "nobody", "non", "none", "noone", "nor", "normally", "not", "nothing", "novel", "now", "nowhere", "obviously", "of", "off", "often", "oh", "ok", "okay", "old", "on", "once", "one", "ones", "only", "onto", "or", "other", "others", "otherwise", "ought", "our", "ours", "ourselves", "out", "outside", "over", "overall", "own", "particular", "particularly", "per", "perhaps", "placed", "please", "plus", "possible", "presumably", "probably", "provides", "que", "quite", "qv", "rather", "rd", "re", "really", "reasonably", "regarding", "regardless", "regards", "relatively", "respectively", "right", "said", "same", "saw", "say", "saying", "says", "second", "secondly", "see", "seeing", "seem", "seemed", "seeming", "seems", "seen", "self", "selves", "sensible", "sent", "serious", "seriously", "seven", "several", "shall", "she", "should", "shouldnt", "since", "six", "so", "some", "somebody", "somehow", "someone", "something", "sometime", "sometimes", "somewhat", "somewhere", "soon", "sorry", "specified", "specify", "specifying", "still", "sub", "such", "sup", "sure", "ts", "take", "taken", "tell", "tends", "th", "than", "thank", "thanks", "thanx", "that", "thats", "thats", "the", "their", "theirs", "them", "themselves", "then", "thence", "there", "theres", "thereafter", "thereby", "therefore", "therein", "theres", "thereupon", "these", "they", "theyd", "theyll", "theyre", "theyve", "think", "third", "this", "thorough", "thoroughly", "those", "though", "three", "through", "throughout", "thru", "thus", "to", "together", "too", "took", "toward", "towards", "tried", "tries", "truly", "try", "trying", "twice", "two", "un", "under", "unfortunately", "unless", "unlikely", "until", "unto", "up", "upon", "us", "use", "used", "useful", "uses", "using", "usually", "value", "various", "very", "via", "viz", "vs", "want", "wants", "was", "wasnt", "way", "we", "wed", "well", "were", "weve", "welcome", "well", "went", "were", "werent", "what", "whats", "whatever", "when", "whence", "whenever", "where", "wheres", "whereafter", "whereas", "whereby", "wherein", "whereupon", "wherever", "whether", "which", "while", "whither", "who", "whos", "whoever", "whole", "whom", "whose", "why", "will", "willing", "wish", "with", "within", "without", "wont", "wonder", "would", "would", "wouldnt", "yes", "yet", "you", "youd", "youll", "youre", "youve", "your", "yours", "yourself", "yourselves", "zero"};
        stopwordsList = Arrays.asList(stopwords);
        //Built up with domain specific information + generic strings
        String[] ignoreEntities = new String[]{"class", "record", "database", "company", "system", "information", "organization",
                "I", "site", "time", "proposition", "display", "log", "choose", "day", "image", "order", "date", "data", "bring", "datum", "show", "term", "list", "number", "term", "reference", "search", "select", "age", "set", "field", "box", "time", "name", "type", "départ", "page", "frame", "option", "tab", "terms", "voyage", "sam", "term"};
        ignoreEntitiesList = Arrays.asList(ignoreEntities);
        String[] ignoreVerbs = new String[]{"go", "goto", "choose", "click", "be", "see", "dim", "box", "nice", "Proceed", "advance"};
        ignoreVerbsList = Arrays.asList(ignoreVerbs);
        stanfordCoreUtils = new StanfordCoreUtils();
        wordNetUtils = new WordNetUtils();
        searchModel = new SearchModel();
        searchModel.collectElements();
        classCount = searchModel.getTotalClassCount().size();
        attrCount = searchModel.getTotalAttrCount().size();
        methodCount = searchModel.getTotalMethodCount().size();
        relationshipCount = searchModel.getTotalRelationshipCount().size();
    }


    public void setupScenario(String text) {
        currentScenario = new Scenario(text);
        List<CoreMap> coreMaps = stanfordCoreUtils.generateSentenceAnnotation(text);
        Collection<RelationTriple> triples = stanfordCoreUtils.getTriples(coreMaps);
        List<TypedDependency> dependencies = stanfordCoreUtils.getDependencies(text);
        currentScenario.setSentenceAnnotation(coreMaps);
        currentScenario.setTriples(triples);
        currentScenario.setDependencies(dependencies);
        setupScenarioDependencies();
        collectUserRole(text);
        for (String s : text.split("\\W+")) {
            if (!s.trim().equals("")) {
                if (!wordNetUtils.isValidWord(s)) {
                    currentScenario.addInvalidWord(s);
                }
            }

        }
        analyseScenario();
    }

    public void setupScenarioDependencies() {
        for (TypedDependency structure : currentScenario.getDependencies()) {
            if (structure.reln().toString().equalsIgnoreCase("compound")) {
                currentScenario.addToCompoundDependencies(structure);
            } else if (structure.reln().toString().equalsIgnoreCase("amod")) {
                currentScenario.addToAmodDependencies(structure);
            } else if (structure.reln().toString().equalsIgnoreCase("nmod:poss")) {
                currentScenario.addToPossDependencies(structure);
            } else if (structure.reln().toString().equalsIgnoreCase("nmod:by")) {
                currentScenario.addToByDependencies(structure);
            }
        }
    }


    public void analyseScenario() {
        analyseSubject();
        analyseObject();
        analyseRelation();

        analyseAmodDependencies();
        analysePossessiveDependencies();

        analyseAttributes();
        analysePotentialRelations();
    }


    public void analyseSubject() {

        Collection<RelationTriple> triples = currentScenario.getTriples();
        if (!triples.isEmpty()) {
            for (RelationTriple triple : triples) {

                String searchElement = triple.subjectLemmaGloss();
                String[] splitElement = searchElement.split("\\W+");

                // SEARCH ROLE ELEMENT
                if (searchElement.equalsIgnoreCase("I")) {

                    if (currentScenario.getSubjectElement() != null) {
                        searchForEntity(currentScenario.getSubjectElement());
                    }


                } else if (splitElement.length == 1) {
                    // CHECK THE LEMMA GLOSS
                    if (isValidOneWordClassName(searchElement))
                        searchForEntity(triple.subjectLemmaGloss());


                    searchForEntity(triple.subjectLemmaGloss());
                } else {

                    String compoundStr = isCompound(searchElement);
                    String amodStr = isAmod(searchElement);

                    if (compoundStr != null) {
                        searchForEntity(compoundStr);
                    } else if (amodStr != null) {
                        searchForEntity(amodStr);
                    } else {

                        searchElement = triple.subjectHead().value();
                        if (isValidOneWordClassName(searchElement))
                            searchForEntity(searchElement);


                    }

                }


            }
        }
    }

    public void analyseObject() {

        Collection<RelationTriple> triples = currentScenario.getTriples();
        if (!triples.isEmpty()) {
            for (RelationTriple triple : triples) {
                String searchElement = triple.objectLemmaGloss();
                String[] splitElement = searchElement.split("\\W+");
                if (searchElement.equalsIgnoreCase("I")) {

                    if (currentScenario.getSubjectElement() != null) {
                        searchForEntity(currentScenario.getSubjectElement());
                    }

                } else if (splitElement.length == 1) {
                    // CHECK THE LEMMA GLOSS
                    if (isValidOneWordClassName(searchElement))
                        searchForEntity(triple.objectLemmaGloss());
                } else {

                    searchElement = triple.objectHead().value();
                    if (isValidOneWordClassName(searchElement))
                        searchForEntity(searchElement);

                }


            }
        }
    }

    public String getObjectEntity(RelationTriple triple) {
        String searchElement = triple.objectLemmaGloss();
        String[] splitElement = searchElement.split("\\W+");
        if (searchElement.equalsIgnoreCase("I") || searchElement.equalsIgnoreCase("me")) {

            if (currentScenario.getRole() != null) {


                if (searchForEntity(currentScenario.getRole()) == 1) {
                    return currentScenario.getRole();
                }
            }

        } else if (splitElement.length == 1) {
            // CHECK THE LEMMA GLOSS
            if (isValidOneWordClassName(searchElement))
                if (searchForEntity(triple.objectLemmaGloss()) == 1) {
                    return triple.objectLemmaGloss();
                }
        } else {

            searchElement = triple.objectHead().value();
            if (isValidOneWordClassName(searchElement))
                if (searchForEntity(searchElement) == 1) {
                    return searchElement;
                }

        }
        return null;
    }

    public String getSubjectEntity(RelationTriple triple) {

        String searchElement = triple.subjectLemmaGloss();
        String[] splitElement = searchElement.split("\\W+");

        // SEARCH ROLE ELEMENT
        if (searchElement.equalsIgnoreCase("I")) {

            if (currentScenario.getRole() != null) {
                if (searchForEntity(currentScenario.getRole()) == 1) {
                    return currentScenario.getRole();
                }
            }


        } else if (splitElement.length == 1) {
            // CHECK THE LEMMA GLOSS
            if (isValidOneWordClassName(searchElement)) {
                if (searchForEntity(triple.subjectLemmaGloss()) == 1) {
                    return triple.subjectLemmaGloss();
                }
            }


            if (searchForEntity(triple.subjectLemmaGloss()) == 1) {
                currentScenario.setRole(triple.subjectLemmaGloss());

            }
        } else {

            String compoundStr = isCompound(searchElement);
            String amodStr = isAmod(searchElement);

            if (compoundStr != null) {
                if (searchForEntity(compoundStr) == 1) {
                    return compoundStr;
                }
            } else if (amodStr != null) {
                if (searchForEntity(amodStr) == 1) {
                    return amodStr;
                }
            } else {

                searchElement = triple.subjectHead().value();
                if (isValidOneWordClassName(searchElement))
                    if (searchForEntity(searchElement) == 1) {
                        return searchElement;
                    }


            }

        }

        return null;
    }

    public void analyseRelation() {
        Collection<RelationTriple> triples = currentScenario.getTriples();
        if (!triples.isEmpty()) {
            for (RelationTriple triple : triples) {

                boolean valObj = true;
                String subjEntity = getSubjectEntity(triple);
                String objEntity = getObjectEntity(triple);
                if (subjEntity != null) {
                    if (objEntity != null) {
                        // method in subj
                        String[] splitStr = triple.relationLemmaGloss().split("\\W+");
                        for (String s : splitStr) {
                            if (ignoreVerbsList.contains(s.toLowerCase()) || isNumeric(triple.relationLemmaGloss())
                                    || !wordNetUtils.isValidWord(triple.relationLemmaGloss())
                                    || stanfordCoreUtils.isProperNoun(triple.relationLemmaGloss())) {
                                valObj = false;
                            }
                        }
                        if (valObj) {
                            String nameMethod = triple.relationLemmaGloss() + objEntity;
                            nameMethod = nameMethod.replaceAll(" ", "");
                            searchForRelationship(subjEntity, objEntity);
                            searchForMethod(nameMethod, subjEntity);
                        }

                    } else {
//                        method in subj with relation to obj
                        String[] splitStr = triple.objectLemmaGloss().split("\\W+");
                        for (String s : splitStr) {
                            if (ignoreVerbsList.contains(s.toLowerCase()) || isNumeric(triple.objectLemmaGloss())
                                    || !wordNetUtils.isValidWord(triple.objectLemmaGloss())
                                    || stanfordCoreUtils.isProperNoun(triple.objectLemmaGloss())) {
                                valObj = false;
                            }
                        }
                        splitStr = triple.relationLemmaGloss().split("\\W+");
                        for (String s : splitStr) {
                            if (ignoreVerbsList.contains(s.toLowerCase()) || isNumeric(triple.relationLemmaGloss())
                                    || !wordNetUtils.isValidWord(triple.relationLemmaGloss())
                                    || stanfordCoreUtils.isProperNoun(triple.relationLemmaGloss())) {
                                valObj = false;
                            }
                        }
                        if (valObj) {

                            String nameMethod = triple.relationLemmaGloss() + triple.objectLemmaGloss();
                            nameMethod = nameMethod.replaceAll(" ", "");
                            searchForMethod(nameMethod, subjEntity);

                        }

                    }

                }

            }
        }
    }


    //    ------------------------------------------
//    SEARCH METHODS
//    ------------------------------------------
    public int searchForEntity(String text) {
        if (text.trim().equals("")){
            return 0;
        }
        if (foundClassList.contains(text.toLowerCase()))
            return 1;
        if (notFoundClassList.contains(text.toLowerCase()))
            return 0;
        if (ignoreEntitiesList.contains(text.trim().toLowerCase()) || stopwordsList.contains(text.trim().toLowerCase())
                || currentScenario.getInvalidWords().contains(text.trim().toLowerCase()))
            return 0;
        String className = text;
        int found = searchModel.searchClass(className);
        if (found == 1) {
            printEntitySearch(className, found);
            //add to found list
            currentScenario.addToFoundClassList(className.toLowerCase());
            foundClassList.add(className.toLowerCase());
            return found;
        }
        // Search lemma
        className = stanfordCoreUtils.getLemma(text);
        found = searchModel.searchClass(className);


        if (found == 1) {
            printEntitySearch(className, found);
            //add to found list
            currentScenario.addToFoundClassList(className.toLowerCase());
            foundClassList.add(className.toLowerCase());
            return found;
        } else {
            printEntitySearch(text, found);
            //add to not found list
            currentScenario.addToNotFoundClassList(className.toLowerCase());
            notFoundClassList.add(className.toLowerCase());
        }
        return found;
    }

    public void searchForMethod(String element, String parent) {
        if (notFoundClassList.contains(parent.toLowerCase()))
            return;
        String searcher = element + "," + parent;
        if (notFoundMethodList.contains(searcher.toLowerCase()))
            return;
        int found = searchForEntity(parent);
        if (found == 1) {
            //add to found list
            currentScenario.addToFoundClassList(parent.toLowerCase());
            foundClassList.add(parent.toLowerCase());

            int methodFound = searchModel.checkMethod(element, parent);
            if (methodFound == 1) {

                String checker = element + "," + parent;
                currentScenario.addToFoundMethodList(checker.toLowerCase());
                foundMethodList.add(checker.toLowerCase());
                printMethodSearch(element, parent, methodFound);
            } else {
                String checker = element + "," + parent;
                currentScenario.addToNotFoundMethodList(checker.toLowerCase());
                notFoundMethodList.add(checker.toLowerCase());
                printMethodSearch(element, parent, methodFound);
            }

        }
    }

    public void searchForAttribute(String element) {
        if (potentialFoundAttributeList.contains(element.toLowerCase() + "," + currentScenario.getText().toLowerCase().replaceAll(",", "")))
            return;
        if (notPotentialFoundAttributeList.contains(element.toLowerCase() + "," + currentScenario.getText().toLowerCase().replaceAll(",", "")))
            return;
        int attrFound = searchModel.checkAttribute(element);
        if (attrFound == 1) {
            potentialFoundAttributeList.add(element.toLowerCase() + "," + currentScenario.getText().toLowerCase().replaceAll(",", ""));
        } else {
            notPotentialFoundAttributeList.add(element.toLowerCase() + "," + currentScenario.getText().toLowerCase().replaceAll(",", ""));
        }
        printPotentialAttributeSearch(element, currentScenario.getText(), attrFound);


    }

    public int searchForAttribute(String element, String parent) {
        if (notFoundClassList.contains(parent.toLowerCase()))
            return 0;
        String searcher = element + "," + parent;
        if (notFoundAttributeList.contains(searcher.toLowerCase()))
            return 0;
        int found = searchForEntity(parent);
        if (found == 1) {
            //add to found list
            currentScenario.addToFoundClassList(parent.toLowerCase());
            foundClassList.add(parent.toLowerCase());

            int attrFound = searchModel.checkAttribute(element, parent);
            if (attrFound == 1) {

                String checker = element + "," + parent;
                currentScenario.addToFoundAttributeList(checker.toLowerCase());
                foundAttributeList.add(checker.toLowerCase());
                printAttributeSearch(element, parent, attrFound);
            } else {
                String checker = element + "," + parent;
                currentScenario.addToNotFoundAttributeList(checker.toLowerCase());
                notFoundAttributeList.add(checker.toLowerCase());
                printAttributeSearch(element, parent, attrFound);
            }

            return found;
        }
        return 0;
    }


    public void searchForRelationship(String element, String objElement) {
        if (element.toLowerCase().equals(objElement.toLowerCase())) {
            return;
        }
        if (notFoundClassList.contains(element.toLowerCase()) || notFoundClassList.contains(objElement.toLowerCase()))
            return;
        String searcher = element + "," + objElement;
        if (notFoundRelationshipList.contains(searcher.toLowerCase()))
            return;
        if (foundRelationshipList.contains(searcher.toLowerCase())) {
            return;
        }
        int found = searchModel.getRelationships(element, objElement);
        if (found == 1) {
            //add to found list
            String input = element + "," + objElement;
            currentScenario.addToFoundRelationshipList(input.toLowerCase());
            foundRelationshipList.add(input.toLowerCase());
            printRelationshipSearch(element, objElement, found);
        } else {
            String input = element + "," + objElement;
            currentScenario.addToNotFoundRelationshipList(input.toLowerCase());
            notFoundRelationshipList.add(input.toLowerCase());
            printRelationshipSearch(element, objElement, found);
        }
    }

    public void searchForRelationship(String element, String objElement, String type) {
        if (element.toLowerCase().equals(objElement.toLowerCase())) {
            return;
        }
        if (notFoundClassList.contains(element.toLowerCase()) || notFoundClassList.contains(objElement.toLowerCase()))
            return;
        String searcher = element + "," + objElement + "," + type;
        if (notFoundRelationshipList.contains(searcher.toLowerCase()))
            return;
        int found = searchModel.getRelationships(element, objElement, type);
        if (found == 1) {
            //add to found list
            String input = element + "," + objElement + "," + type;
            currentScenario.addToFoundRelationshipList(input.toLowerCase());
            foundRelationshipList.add(input.toLowerCase());
            printRelationshipSearch(element, objElement, type, found);
        } else {
            String input = element + "," + objElement + "," + type;
            currentScenario.addToNotFoundRelationshipList(input.toLowerCase());
            notFoundRelationshipList.add(input.toLowerCase());
            printRelationshipSearch(element, objElement, type, found);
        }
    }


    //    ------------------------------------------
//    PRINT METHODS
//    ------------------------------------------
    public void printEntitySearch(String text, int found) {
        String printMsg = "SEARCHED CLASS ENTITY:\"" + text + "\"";
        if (found == 1) {
            printMsg += " >> CORRECTLY IDENTIFIED ELEMENT";
        } else {
            printMsg += " >> ELEMENT IDENTIFIED INCORRECTLY";
        }
        System.out.println(printMsg);
    }

    public void printPotentialAttributeSearch(String text, String sentence, int found) {
        String printMsg = "SEARCHED ATTRIBUTE :\"" + text + "\" IN UNKNOWN CLASS FROM SENTENCE: " + sentence;
        if (found == 1) {
            printMsg += " >> CORRECTLY IDENTIFIED ELEMENT";
        } else {
            printMsg += " >> ELEMENT IDENTIFIED INCORRECTLY";
        }
        System.out.println(printMsg);
    }

    public void printAttributeSearch(String text, String parent, int found) {
        String printMsg = "SEARCHED ATTRIBUTE :\"" + text + "\" IN CLASS: \"" + parent + "\"";
        if (found == 1) {
            printMsg += " >> CORRECTLY IDENTIFIED ELEMENT";
        } else {
            printMsg += " >> ELEMENT IDENTIFIED INCORRECTLY";
        }
        System.out.println(printMsg);
    }

    public void printMethodSearch(String text, String parent, int found) {
        String printMsg = "SEARCHED METHOD :\"" + text + "()\" IN CLASS: \"" + parent + "\"";
        if (found == 1) {
            printMsg += " >> CORRECTLY IDENTIFIED ELEMENT";
        } else {
            printMsg += " >> ELEMENT IDENTIFIED INCORRECTLY";
        }
        System.out.println(printMsg);
    }

    public void printRelationshipSearch(String element1, String element2, String type, int found) {
        String printMsg = "SEARCHED RELATIONSHIP BETWEEN CLASSES:\"" + element1 + "\" AND \"" + element2 + "\" OF TYPE " + type;
        if (found == 1) {
            printMsg += " >> CORRECTLY IDENTIFIED ELEMENT";
        } else {
            printMsg += " >> ELEMENT IDENTIFIED INCORRECTLY";
        }
        System.out.println(printMsg);
    }

    public void printRelationshipSearch(String element1, String element2, int found) {
        String printMsg = "SEARCHED RELATIONSHIP BETWEEN CLASSES:\"" + element1 + "\" AND \"" + element2 + "\"";

        if (found == 1) {
            printMsg += " >> CORRECTLY IDENTIFIED ELEMENT";
        } else {
            printMsg += " >> ELEMENT IDENTIFIED INCORRECTLY";
        }
        System.out.println(printMsg);
    }


    public void collectUserRole(String text) {
        String prefix = "as a ";
        String altPrefix = "as an ";
        if (text.toLowerCase().startsWith(prefix)) {

            String result = text.toLowerCase().substring(prefix.length());
            String rootRes = "";
            List<TypedDependency> dependencies = stanfordCoreUtils.getDependencies(result);
            for (TypedDependency dependency : dependencies) {
                if (dependency.reln().toString().equalsIgnoreCase("root")) {
                    rootRes = dependency.dep().value();
                    currentScenario.setRole(rootRes);
                }
            }
            for (TypedDependency dependency : dependencies) {
                if (!rootRes.equals("")) {
                    if (dependency.reln().toString().equalsIgnoreCase("amod")) {
                        String funcRole = checkAmodElement(dependency.gov().value(), dependency);
                        if (funcRole != null) {
                            if (!funcRole.trim().equalsIgnoreCase("")) {
                                currentScenario.setRole(funcRole);
                            }
                        }
                    } else if (dependency.reln().toString().equalsIgnoreCase("compound")) {
                        String role = checkCompoundDependency(rootRes, dependency);
                        if (role != null) {
                            currentScenario.setRole(role);
                            userRole = role;

                        }
                    }

                }
            }

        } else if (text.toLowerCase().startsWith(altPrefix)) {
            String rootRes = "";

            String result = text.toLowerCase().substring(altPrefix.length());
            List<TypedDependency> dependencies = stanfordCoreUtils.getDependencies(result);
            for (TypedDependency dependency : dependencies) {
                if (dependency.reln().toString().equalsIgnoreCase("root")) {
                    rootRes = dependency.dep().value();
                    currentScenario.setRole(rootRes);
                }
            }

            for (TypedDependency dependency : dependencies) {
                if (!rootRes.equals("")) {
                    if (dependency.reln().toString().equalsIgnoreCase("amod")) {
                        String funcRole = checkAmodElement(dependency.gov().value(), dependency);
                        if (!funcRole.trim().equalsIgnoreCase("")) {
                            currentScenario.setRole(funcRole);

                        }
                    } else if (dependency.reln().toString().equalsIgnoreCase("compound")) {
                        String role = checkCompoundDependency(rootRes, dependency);
                        currentScenario.setRole(role);
                        userRole = role;
                    }

                }

            }
        } else {
            currentScenario.setRole(userRole);
        }
        userRole = currentScenario.getRole();

    }


    public void setUserRole(String role) {
        this.userRole = role;
    }


    //    ------------------------------------------
//    LANG CHECK METHODS
//    ------------------------------------------
    public String checkAmodElement(String text, TypedDependency typedDependency) {

        String el1 = typedDependency.gov().value().toLowerCase();
        String el2 = typedDependency.dep().value().toLowerCase();

        String modifier = stanfordCoreUtils.getLemma(el2);
        String element = stanfordCoreUtils.getLemma(el1);

        if (typedDependency.gov().value().toLowerCase().equalsIgnoreCase(text)) {
            if (isValidOneWordClassName(el1) || isValidOneWordClassName(text)) {
                if ((!isNumeric(modifier)) && (isMinLength(modifier) && (!stanfordCoreUtils.isProperNoun(modifier))
                        && (stanfordCoreUtils.ignoreNerEntityAnnotations(modifier)) && (wordNetUtils.isValidWord(modifier)))) {
                    if (searchForEntity(element) == 1) {
                        return element;
                    }
                    String s = stanfordCoreUtils.getLemma(element);
                    if (searchForEntity(s) == 1) {
                        return s;
                    }

                    if (searchForAttribute(modifier, element) == 1) {
                        return element;
                    }
                    if (searchForAttribute(modifier, s) == 1) {
                        return element;
                    }
                }

            }
        }
        return null;
    }

    public String isAmod(String text) {

        boolean amodFound = false;
        String[] splitText = text.split("\\W+");
        List<String> strlist = new ArrayList<>(Arrays.asList(splitText));
        for (int i = 0; i < strlist.size(); i++) {
            if (stopwordsList.contains(strlist.get(i).toLowerCase()))
                strlist.remove(i);
        }
        if (strlist.size() == 2) {


            String element1 = strlist.get(0);
            String element2 = strlist.get(1);

            String lemma1 = stanfordCoreUtils.getLemma(element1);
            String lemma2 = stanfordCoreUtils.getLemma(element2);


            String modifier = "";
            String element = "";

            for (TypedDependency typedDependency : currentScenario.getAmodDependencies()) {
                String el1 = typedDependency.gov().value().toLowerCase();
                String el2 = typedDependency.dep().value().toLowerCase();

                String amodLemma1 = stanfordCoreUtils.getLemma(el1);
                String amodLemma2 = stanfordCoreUtils.getLemma(el2);

                if ((element1.equalsIgnoreCase(el1) && element2.equalsIgnoreCase(el2))
                        || (element2.equalsIgnoreCase(el1) && element1.equalsIgnoreCase(el2))
                        || (lemma1.equalsIgnoreCase(amodLemma1) && lemma2.equalsIgnoreCase(amodLemma2))
                        || (lemma1.equalsIgnoreCase(amodLemma2) && lemma2.equalsIgnoreCase(amodLemma1))) {


                    modifier = amodLemma2;
                    element = amodLemma1;

                    amodFound = true;
                }
            }

            if (amodFound && (!isNumeric(element1) && !isNumeric(element2)) && (isMinLength(element1)
                    && isMinLength(element2)) && (!stanfordCoreUtils.isProperNoun(element1)
                    && !stanfordCoreUtils.isProperNoun(element2)) && (stanfordCoreUtils.ignoreNerEntityAnnotations(element1)
                    && stanfordCoreUtils.ignoreNerEntityAnnotations(element2)) && (wordNetUtils.isValidWord(element1) && wordNetUtils.isValidWord(element2))) {

                searchForEntity(element);
                String s = stanfordCoreUtils.getLemma(element);
                searchForEntity(s);

                if (searchForAttribute(modifier, element) == 1) {
                    return element;
                }
                if (searchForAttribute(modifier, s) == 1) {
                    return element;
                }
            }
        }
        return null;
    }

    public String checkCompoundDependency(String text, TypedDependency dependency) {
        String compoundElement = dependency.dep().value();
        String compoundLemma = stanfordCoreUtils.getLemma(compoundElement);
        String rootLemma = stanfordCoreUtils.getLemma(text);
        if (isValidOneWordClassName(compoundLemma)) {
            if (searchForEntity(StringUtils.capitalize(compoundLemma) + StringUtils.capitalize(rootLemma)) == 1) {
                return compoundLemma + rootLemma;
            }
        }
        return null;
    }

    public String isCompound(String text) {

        //COMPOUND NOUNS = POTENTIAL CLASSES
        String[] splitText = text.split("\\W+");
        List<String> strlist = new ArrayList<>(Arrays.asList(splitText));
        for (int i = 0; i < strlist.size(); i++) {
            if (stopwordsList.contains(strlist.get(i).toLowerCase())) {
                strlist.remove(i);
            }
            if (ignoreEntitiesList.contains(strlist.get(i).toLowerCase())) {
                strlist.remove(i);
            }
        }
        if (strlist.size() == 2) {

            String el1 = strlist.get(0);
            String el2 = strlist.get(1);

            if (!isValidOneWordClassName(el1) || (!isValidOneWordClassName(el2))) {
                return null;
            }

            for (TypedDependency typedDependency : currentScenario.getCompoundDependencies()) {
                if (typedDependency.gov().value().equalsIgnoreCase(el2) && typedDependency.dep().value().equalsIgnoreCase(el1)) {

                    String searchEl = StringUtils.capitalize(typedDependency.dep().value())
                            + StringUtils.capitalize(typedDependency.gov().value());

                    if (searchForEntity(searchEl) == 1) {
                        return searchEl;
                    }
                }
            }
        }
        return null;
    }

    public boolean isValidOneWordClassName(String word) {
        String[] splitCheck = word.split("\\W+");
        if (splitCheck.length != 1) return false;

        return isMinLength(word) && !isNumeric(word) && !stanfordCoreUtils.isProperNoun(word) && (!stopwordsList.contains(word.toLowerCase()) && !ignoreEntitiesList.contains(word.toLowerCase()))
                && stanfordCoreUtils.ignoreNerEntityAnnotations(word) && wordNetUtils.isValidWord(word) && !notFoundClassList.contains(word.toLowerCase());
    }


    public boolean isMinLength(String word) {
        return word.length() > 2;
    }

    public boolean isNumeric(String word) {
        Pattern pattern = Pattern.compile("-?\\d+(\\.\\d+)?");
        if (word == null) {
            return false;
        }
        return pattern.matcher(word).matches();
    }


    public void appendExtractedLists() {
        foundClassList.addAll(currentScenario.getFoundClassList());
        notFoundClassList.addAll(currentScenario.getNotFoundClassList());
        foundAttributeList.addAll(currentScenario.getFoundAttributeList());
        notFoundAttributeList.addAll(currentScenario.getNotFoundAttributeList());
        foundMethodList.addAll(currentScenario.getFoundMethodList());
        notFoundMethodList.addAll(currentScenario.getNotFoundMethodList());
        foundRelationshipList.addAll(currentScenario.getFoundRelationshipList());
        notFoundRelationshipList.addAll(currentScenario.getNotFoundRelationshipList());
    }

    public HashSet<String> getFoundClassList() {
        return foundClassList;
    }

    public HashSet<String> getNotFoundClassList() {
        return notFoundClassList;
    }

    public HashSet<String> getFoundAttributeList() {
        return foundAttributeList;
    }

    public HashSet<String> getNotFoundAttributeList() {
        return notFoundAttributeList;
    }

    public HashSet<String> getFoundMethodList() {
        return foundMethodList;
    }

    public HashSet<String> getNotFoundMethodList() {
        return notFoundMethodList;
    }

    public HashSet<String> getFoundRelationshipList() {
        return foundRelationshipList;
    }

    public HashSet<String> getNotFoundRelationshipList() {
        return notFoundRelationshipList;
    }


    public int getClassCount() {
        return classCount;
    }

    public int getAttrCount() {
        return attrCount;
    }

    public int getMethodCount() {
        return methodCount;
    }

    public int getRelationshipCount() {
        return relationshipCount;
    }

    public void analysePossessiveDependencies() {
        HashSet<TypedDependency> possDependencies = currentScenario.getPossDependencies();
        for (TypedDependency dependency : possDependencies) {


            String element = dependency.dep().value();
            if (element.equalsIgnoreCase("my")) {

                String role = currentScenario.getRole();
                String lemma = dependency.gov().value();

                int found = searchForAttribute(lemma, role);
                if (found != 1) {
                    lemma = stanfordCoreUtils.getLemma(lemma);
                    found = searchForAttribute(lemma, role);
                }
                if (found == 1) {
                    int foundClass = searchForEntity(lemma);
                    if (foundClass == 1) {
                        searchForRelationship(role, lemma, SearchModel.COMPOSITION);
                    }
                }
            }
        }
    }

    public void checkFields(String text) {
        if (text.toLowerCase().contains("the field “")) {
            String line = text.toLowerCase();
            line = line.substring(line.indexOf("the field “"));
            String element = line.substring(line.indexOf("“"));
            element = element.replaceAll("”", "");
            element = element.replaceAll("“", "");
            element = element.replaceAll(" ", "");
            searchForAttribute(element);
        } else if (text.toLowerCase().contains("the field \"")) {
            String line = text.toLowerCase();
            line = line.substring(line.indexOf("the field \""));
            String element = line.substring(line.indexOf("\""));
            element = element.replaceAll("\"", "");
            element = element.replaceAll(" ", "");
            searchForAttribute(element);
        }
    }

    public void analyseAttributes() {

        String text = currentScenario.getText();
        if (text.toLowerCase().contains("the field \"") || text.toLowerCase().contains("the field “")) {
            checkFields(text);
        }
    }


    public void analysePotentialRelations() {
        List<CoreMap> sentences = stanfordCoreUtils.getSentenceAnnotations(currentScenario.getText());
        if (!sentences.isEmpty()) {
            for (CoreMap map : sentences) {
                for (CoreLabel token : map.get(CoreAnnotations.TokensAnnotation.class)) {
                    String word = token.get(CoreAnnotations.TextAnnotation.class);
                    String pos = token.get(CoreAnnotations.PartOfSpeechAnnotation.class);
                    //simple verb indicates potential relationship
                    if (pos.toLowerCase().equals("vb") && !ignoreVerbsList.contains(word)) {
                        String text = currentScenario.getText().trim().replaceAll("\n", " ").replaceAll("\r", " ");
                        potentialRelationList.add(word + "," + text);
                    }
                }
            }
        }
    }

    public void analyseAmodDependencies() {
        HashSet<TypedDependency> amodDependencies = currentScenario.getAmodDependencies();
        for (TypedDependency dependency : amodDependencies) {

            String entity = dependency.gov().value();
            String modifier = dependency.dep().value();


            String entityLemma = stanfordCoreUtils.getLemma(entity);
            String modifierLemma = stanfordCoreUtils.getLemma(modifier);
            searchForAttribute(modifier, entityLemma);
            searchForAttribute(modifierLemma, entityLemma);
        }
    }


    public HashSet<String> getPotentialFoundAttributeList() {
        return potentialFoundAttributeList;
    }

    public HashSet<String> getNotPotentialFoundAttributeList() {
        return notPotentialFoundAttributeList;
    }

    public HashSet<String> getPotentialRelationList() {
        return potentialRelationList;
    }
}
