package fyp;


import br.gov.frameworkdemoiselle.behave.parser.Step;
import edu.stanford.nlp.ling.CoreAnnotations;
import edu.stanford.nlp.ling.CoreLabel;
import edu.stanford.nlp.pipeline.Annotation;
import edu.stanford.nlp.pipeline.StanfordCoreNLP;
import edu.stanford.nlp.trees.Tree;
import edu.stanford.nlp.trees.TreeCoreAnnotations;
import edu.stanford.nlp.util.CoreMap;
import org.jbehave.core.annotations.Given;
import org.jbehave.core.annotations.Then;
import org.jbehave.core.annotations.When;

import java.util.ArrayList;
import java.util.List;
import java.util.Properties;

import static org.junit.Assert.assertEquals;

public class MySteps implements Step {

    private static StanfordCoreNLP pipeline = null;
    private MyXML xml = new MyXML();


    @Given("I go to \"$local\"")
    @When("I go to \"$local\"")
    @Then("I go to \"$local\"")
    public void givenGoTo(String local) {
        String[] words = local.split("\\W+");
        for (int i = 0; i < words.length; i++) {

            // CHECK CLASS
            if (heuristicE1(words[i]) && heuristicE9(words[i]) && heuristicE11_E12(words[i])) {
                assertEquals(1, xml.checkClassNodes(words[i]));
//                xml.checkClassNodes(words[i]);
            }
            //CHECK ATTR
            else if (heuristicA1(words[i]) || heuristicA2(words[i])) {
                assertEquals(1, xml.checkAttributeNodes(words[i]));
//                xml.checkAttributeNodes(words[i]);
            }

            //CHECK RELATIONSHIP/METHOD
            else if (heuristicR1(words[i]) || heuristicR4(words[i])) {
                assertEquals(1, xml.checkMethodNodes(words[i]));
//                xml.checkMethodNodes(words[i]);
            }
        }

    }

    @Given("\"$local\" is displayed")
    @Then("\"$local\" is displayed")
    @When("\"$local\" is displayed")
    public void pageWithNameInEnglish(String local) {
        String[] words = local.split("\\W+");
        for (int i = 0; i < words.length; i++) {

            if (heuristicE1(words[i]) && heuristicE9(words[i]) && heuristicE11_E12(words[i])) {
                assertEquals(1, xml.checkClassNodes(words[i]));
            }
            else if (heuristicA1(words[i]) || heuristicA2(words[i])) {
                assertEquals(1, xml.checkAttributeNodes(words[i]));
            }


            else if (heuristicR1(words[i]) || heuristicR4(words[i])) {
                assertEquals(1, xml.checkMethodNodes(words[i]));
            }
        }
    }

    @When(value = "I click on \"$elementName\" referring to \"$locatorParameters\"", priority = 10)
    @Then(value = "I click on \"$elementName\" referring to \"$locatorParameters\"", priority = 10)
    public void clickButtonWithParametersInEnglish(String elementName, String locatorParameters) {
        System.out.println("SEARCH WORD: " + elementName + " , " + locatorParameters);
        String[] words = elementName.split("\\W+");
        for (int i = 0; i < words.length; i++) {
            if (heuristicE1(words[i]) && heuristicE9(words[i]) && heuristicE11_E12(words[i])) {
                assertEquals(1, xml.checkClassNodes(words[i]));
            }
            else if (heuristicA1(words[i]) || heuristicA2(words[i])) {
                assertEquals(1, xml.checkAttributeNodes(words[i]));
            }

            else if (heuristicR1(words[i]) || heuristicR4(words[i])) {
                assertEquals(1, xml.checkMethodNodes(words[i]));
            }
        }
//        assertEquals(1,xml.checkNodes("Click on "+elementName,locatorParameters));
    }

    @When(value = "I click on \"$elementName\"", priority = 1)
    @Then(value = "I click on \"$elementName\"", priority = 1)
    public void clickButtonInEnglish(String elementName) {
        System.out.println("SEARCH WORD: " + elementName);
        String[] words = elementName.split("\\W+");
        for (int i = 0; i < words.length; i++) {
            if (heuristicE1(words[i]) && heuristicE9(words[i]) && heuristicE11_E12(words[i])){
                assertEquals(1, xml.checkClassNodes(words[i]));
            }
            else if (heuristicA1(words[i]) || heuristicA2(words[i])) {
                assertEquals(1, xml.checkAttributeNodes(words[i]));
            }

            //CHECK RELATIONSHIP/METHOD
            else if (heuristicR1(words[i]) || heuristicR4(words[i])) {
                assertEquals(1, xml.checkMethodNodes(words[i]));
            }
        }
    }

    @When(value = "I submit \"$elementName\"", priority = 1)
    @Then(value = "I submit \"$elementName\"", priority = 1)
    public void submitInEnglish(String elementName) {
        String[] words = elementName.split("\\W+");
        for (int i = 0; i < words.length; i++) {
            if (heuristicE1(words[i]) && heuristicE9(words[i]) && heuristicE11_E12(words[i])) {
                assertEquals(1, xml.checkClassNodes(words[i]));
            }
            else if (heuristicA1(words[i]) || heuristicA2(words[i])) {
                assertEquals(1, xml.checkAttributeNodes(words[i]));
            }

            //CHECK RELATIONSHIP/METHOD
            else if (heuristicR1(words[i]) || heuristicR4(words[i])) {
                assertEquals(1, xml.checkMethodNodes(words[i]));
            }
        }
    }


    @When(value = "I choose \"$elementName\" referring to \"$locatorParameters\"", priority = 10)
    @Then(value = "I choose \"$elementName\" referring to \"$locatorParameters\"", priority = 10)
    public void informWithParametersInEnglish(String elementName, String locatorParameters) {
//        assertEquals(1,xml.checkNodes("Choose "+elementName,locatorParameters));
    }

    @When(value = "I choose \"$fieldName\"", priority = 1)
    @Then(value = "I choose \"$fieldName\"", priority = 1)
    public void informInEnglish(String fieldName) {
        // CHECK CLASS
        String[] words = fieldName.split("\\W+");
        for (int i = 0; i < words.length; i++) {
            if (heuristicE1(words[i]) && heuristicE9(words[i]) && heuristicE11_E12(words[i])) {
                assertEquals(1, xml.checkClassNodes(words[i]));
            }
            else if (heuristicA1(words[i]) || heuristicA2(words[i])) {
                assertEquals(1, xml.checkAttributeNodes(words[i]));
            }

            //CHECK RELATIONSHIP/METHOD
            else if (heuristicR1(words[i]) || heuristicR4(words[i])) {
                assertEquals(1, xml.checkMethodNodes(words[i]));
            }
        }
//        assertEquals(1,xml.checkClassNodes("Choose "+fieldName));
    }

    @When(value = "I select \"$fieldName\"", priority = 1)
    @Then(value = "I select \"$fieldName\"", priority = 1)
    public void selectInEnglish(String fieldName) {
        String[] words = fieldName.split("\\W+");
        for (int i = 0; i < words.length; i++) {
            if (heuristicE1(words[i]) && heuristicE9(words[i]) && heuristicE11_E12(words[i])) {
                assertEquals(1, xml.checkClassNodes(words[i]));
            }
            else if (heuristicA1(words[i]) || heuristicA2(words[i])) {
                assertEquals(1, xml.checkAttributeNodes(words[i]));
            }

            //CHECK RELATIONSHIP/METHOD
            else if (heuristicR1(words[i]) || heuristicR4(words[i])) {
                assertEquals(1, xml.checkMethodNodes(words[i]));
            }
        }

        assertEquals(1, xml.checkClassNodes("Select " + fieldName));
    }

    @When(value = "I choose by index \"$indice\" in the field \"$fieldName\"", priority = 10)
    @Then(value = "I choose by index \"$indice\" in the field \"$fieldName\"", priority = 10)
    public void selectByIndexInEnglish(String indice, String fieldName) {


        if (heuristicE1(indice) || heuristicE9(indice)) {
            assertEquals(1, xml.checkClassNodes(indice));
        }

        if (heuristicE1(fieldName) || heuristicE9(fieldName)) {
            assertEquals(1, xml.checkClassNodes(fieldName));
        }
//        assertEquals(1,xml.checkNodes("Choose "+fieldName,indice));
    }

    @When(value = "I choose the option of value \"$value\" in the field \"$fieldName\"", priority = 20)
    @Then(value = "I choose the option of value \"$value\" in the field \"$fieldName\"", priority = 20)
    public void selectByValueInEnglish(String value, String fieldName) {
        if (heuristicE1(value) || heuristicE9(value)) {
            assertEquals(1, xml.checkClassNodes(value));
        }
        if (heuristicE1(fieldName) || heuristicE9(fieldName)) {
            assertEquals(1, xml.checkClassNodes(fieldName));
        }
//        assertEquals(1,xml.checkNodes("Choose "+fieldName,value));
    }

    @When(value = "I set \"$value\" in the field \"$fieldName\"", priority = 1)
    @Then(value = "I set \"$value\" in the field \"$fieldName\"", priority = 1)
    public void informInEnglish(String value, String fieldName) {
        if (heuristicE1(value) || heuristicE9(value)) {
            assertEquals(1, xml.checkClassNodes(value));
        }
        if (heuristicE1(fieldName) || heuristicE9(fieldName)) {
            assertEquals(1, xml.checkClassNodes(fieldName));
        }

//        assertEquals(1,xml.checkNodes("Set "+fieldName,value));
    }

    @When(value = "I inform \"$value\" and choose \"$selectValue\" in the field \"$fieldName\"", priority = 10)
    @Then(value = "I inform \"$value\" and choose \"$selectValue\" in the field \"$fieldName\"", priority = 10)
    public void informInEnglish(String value, String selectValue, String fieldName) {
        if (heuristicE1(value) || heuristicE9(value)) {
            assertEquals(1, xml.checkClassNodes(value));
        }
        if (heuristicE1(selectValue) || heuristicE9(selectValue)) {
            assertEquals(1, xml.checkClassNodes(selectValue));
        }
        if (heuristicE1(fieldName) || heuristicE9(fieldName)) {
            assertEquals(1, xml.checkClassNodes(fieldName));
        }


//        assertEquals(1,xml.checkNodes("Inform "+fieldName,value));
//        assertEquals(1,xml.checkNodes("Choose "+fieldName,selectValue));
    }

    @When(value = "I type \"$value\" and choose \"$selectValue\" in the field \"$fieldName\"", priority = 10)
    @Then(value = "I type \"$value\" and choose \"$selectValue\" in the field \"$fieldName\"", priority = 10)
    public void typeInEnglish(String value, String selectValue, String fieldName) {
        if (heuristicE1(value) || heuristicE9(value)) {
            assertEquals(1, xml.checkClassNodes(value));
        }
        if (heuristicE1(selectValue) || heuristicE9(selectValue)) {
            assertEquals(1, xml.checkClassNodes(selectValue));
        }
        if (heuristicE1(fieldName) || heuristicE9(fieldName)) {
            assertEquals(1, xml.checkClassNodes(fieldName));
        }


//        assertEquals(1,xml.checkNodes("Type "+fieldName,value));
//        assertEquals(1,xml.checkNodes("Choose "+fieldName,selectValue));
    }

    @When("I reset the value of the field \"$fieldName\"")
    public void resetInEnglish(String fieldName) {
        if (heuristicE1(fieldName) || heuristicE9(fieldName)) {
            assertEquals(1, xml.checkClassNodes(fieldName));
        }
//        assertEquals(1, xml.checkClassNodes("Reset the value of " + fieldName));
    }

    @When("I do not type any value to the field \"$fieldName\"")
    public void notInformInEnglish(String fieldName) {
        if (heuristicE1(fieldName) || heuristicE9(fieldName)) {
            assertEquals(1, xml.checkClassNodes(fieldName));
        }
//        assertEquals(1, xml.checkClassNodes("Do not type any value to " + fieldName));
    }

    @Then("will be displayed \"$text\"")
    public void textVisibleInEnglish(String text) {
        if (heuristicE1(text) || heuristicE9(text)) {
            assertEquals(1, xml.checkClassNodes(text));
        }
        //        assertEquals(1, xml.checkClassNodes("Display " + text));
    }

    @Then("will not be displayed \"$text\"")
    public void textNotVisibleInEnglish(String text) {
        if (heuristicE1(text) || heuristicE9(text)) {
            assertEquals(1, xml.checkClassNodes(text));
        }
    }

    @Then("will be displayed in the field \"$elementName\" the value \"$text\"")
    public void textVisibleInElementInEnglish(String elementName, String text) {
        if (heuristicE1(elementName) || heuristicE9(elementName)) {
            assertEquals(1, xml.checkClassNodes(elementName));
        }
        if (heuristicE1(text) || heuristicE9(text)) {
            assertEquals(1, xml.checkClassNodes(text));
        }
    }

    @Then("will not be displayed in the field \"$elementName\" the value \"$text\"")
    public void textNotVisibleInElementInEnglish(String elementName, String text) {
        if (heuristicE1(elementName) || heuristicE9(elementName)) {
            assertEquals(1, xml.checkClassNodes(elementName));
        }
        if (heuristicE1(text) || heuristicE9(text)) {
            assertEquals(1, xml.checkClassNodes(text));
        }
    }

    @Then("will be displayed the value \"$text\" in \"$elementName\" referring to \"$locatorParameters\"")
    public void textVisibleInElementWithParametersInEnglish(String text, String elementName, List<String> locatorParameters) {
        if (heuristicE1(text) || heuristicE9(text)) {
            assertEquals(1, xml.checkClassNodes(text));
        }
        if (heuristicE1(elementName) || heuristicE9(elementName)) {
            assertEquals(1, xml.checkClassNodes(elementName));
        }
        for (String data : locatorParameters) {
            if (heuristicE1(data) || heuristicE9(data)) {
                assertEquals(1, xml.checkClassNodes(data));
            }
        }

    }

    @Then("will not be displayed the value \"$text\" in \"$elementName\" referring to \"$locatorParameters\"")
    public void textNotVisibleInElementWithParametersInEnglish(String text, String elementName, List<String> locatorParameters) {
        if (heuristicE1(text) || heuristicE9(text)) {
            assertEquals(1, xml.checkClassNodes(text));
        }
        if (heuristicE1(elementName) || heuristicE9(elementName)) {
            assertEquals(1, xml.checkClassNodes(elementName));
        }
        for (String data : locatorParameters) {
            if (heuristicE1(data) || heuristicE9(data)) {
                assertEquals(1, xml.checkClassNodes(data));
            }
        }
    }

    @Given(value = "\"$elementName\" is not visible", priority = 10)
    @When(value = "\"$elementName\" is not visible", priority = 10)
    @Then(value = "\"$elementName\" is not visible", priority = 10)
    public void elementNotVisibleInEnglish(String elementName) {
        if (heuristicE1(elementName) || heuristicE9(elementName)) {
            assertEquals(1, xml.checkClassNodes(elementName));
        }
    }

    @Given(value = "\"$elementName\" referring to \"$locatorParameters\" is not visible", priority = 11)
    @When(value = "\"$elementName\" referring to \"$locatorParameters\" is not visible", priority = 11)
    @Then(value = "\"$elementName\" referring to \"$locatorParameters\" is not visible", priority = 11)
    public void elementWithParametersNotVisibleInEnglish(String elementName, List<String> locatorParameters) {
        if (heuristicE1(elementName) || heuristicE9(elementName)) {
            assertEquals(1, xml.checkClassNodes(elementName));
        }
        for (String data : locatorParameters) {
            if (heuristicE1(data) || heuristicE9(data)) {
                assertEquals(1, xml.checkClassNodes(data));
            }
        }
    }

    @Given(value = "I wait the element \"$fieldName\" be visible, clickable and enabled", priority = 10)
    @When(value = "I wait the element \"$fieldName\" be visible, clickable and enabled", priority = 10)
    @Then(value = "I wait the element \"$fieldName\" be visible, clickable and enabled", priority = 10)
    public void elementVisibleClicableEnableInEnglish(String fieldName) {
        if (heuristicE1(fieldName) || heuristicE9(fieldName)) {
            assertEquals(1, xml.checkClassNodes(fieldName));
        }
    }

    @Given(value = "I wait the element \"$fieldName\" referring to \"$locatorParameters\" be visible, clickable and enabled", priority = 11)
    @When(value = "I wait the element \"$fieldName\" referring to \"$locatorParameters\" be visible, clickable and enabled", priority = 11)
    @Then(value = "I wait the element \"$fieldName\" referring to \"$locatorParameters\" be visible, clickable and enabled", priority = 11)
    public void elementWithParametersVisibleClicableEnableInEnglish(String fieldName, List<String> locatorParameters) {
        if (heuristicE1(fieldName) || heuristicE9(fieldName)) {
            assertEquals(1, xml.checkClassNodes(fieldName));
        }
        for (String data : locatorParameters) {
            if (heuristicE1(data) || heuristicE9(data)) {
                assertEquals(1, xml.checkClassNodes(data));
            }
        }
    }

    @Given(value = "the element \"$fieldName\" is visible and disabled", priority = 10)
    @When(value = "the element \"$fieldName\" is visible and disabled", priority = 10)
    @Then(value = "the element \"$fieldName\" is visible and disabled", priority = 10)
    public void elementVisibleDisableInEnglish(String fieldName) {
        if (heuristicE1(fieldName) || heuristicE9(fieldName)) {
            assertEquals(1, xml.checkClassNodes(fieldName));
        }
    }

    @Given(value = "the element \"$fieldName\" referring to \"$locatorParameters\" is visible and disabled", priority = 11)
    @When(value = "the element \"$fieldName\" referring to \"$locatorParameters\" is visible and disabled", priority = 11)
    @Then(value = "the element \"$fieldName\" referring to \"$locatorParameters\" is visible and disabled", priority = 11)
    public void elementWithParametersVisibleDisableInEnglish(String fieldName, List<String> locatorParameters) {
        if (heuristicE1(fieldName) || heuristicE9(fieldName)) {
            assertEquals(1, xml.checkClassNodes(fieldName));
        }
        for (String data : locatorParameters) {
            if (heuristicE1(data) || heuristicE9(data)) {
                assertEquals(1, xml.checkClassNodes(data));
            }
        }
    }


    //CLASS HEURISTICS

    /*
     *   (RULE HEAD) Noun
     *   (RULE TAIL) Potential Entity
     */
    public boolean heuristicE1(String text) {
        StanfordCoreNLP pipeline = getInstance();
        Annotation doc = new Annotation(text);
        pipeline.annotate(doc);
        List<CoreMap> sentences = doc.get(CoreAnnotations.SentencesAnnotation.class);
        for (CoreMap map : sentences) {
            for (CoreLabel token : map.get(CoreAnnotations.TokensAnnotation.class)) {
                String word = token.get(CoreAnnotations.TextAnnotation.class);
                String pos = token.get(CoreAnnotations.PartOfSpeechAnnotation.class);
                String ner = token.get(CoreAnnotations.NamedEntityTagAnnotation.class);
                System.out.println(word + " , " + pos);
                if (pos.contains("NN"))
                    return true;
            }
        }
        return false;
    }

    /*
     *   (RULE HEAD) Subject
     *   (RULE TAIL) Entity
     */
    public boolean heuristicE5(String text) {
        return false;
    }

    /*
     *   (RULE HEAD) Gerund
     *   (RULE TAIL) Entity
     */
    public boolean heuristicE7(String text) {
        StanfordCoreNLP pipeline = getInstance();
        Annotation doc = new Annotation(text);
        pipeline.annotate(doc);
        List<CoreMap> sentences = doc.get(CoreAnnotations.SentencesAnnotation.class);
        for (CoreMap map : sentences) {
            for (CoreLabel token : map.get(CoreAnnotations.TokensAnnotation.class)) {
                String word = token.get(CoreAnnotations.TextAnnotation.class);
                String pos = token.get(CoreAnnotations.PartOfSpeechAnnotation.class);
                System.out.println(word + " , " + pos);
                if (pos.equalsIgnoreCase("VBG"))

                    return true;
            }
        }
        return false;
    }


    /*
     * (RULE HEAD) Noun such as { “record”, “database”, “company”, “system”, “information” and “organization”, etc.}
     * (RULE TAIL) Ignore as Entity
     */
    public boolean heuristicE9(String text) {
        String[] ignoreEntities = {"record", "database", "company", "system", "information", "organization"};
        for (int i = 0; i < ignoreEntities.length; i++) {
            if (text.equals(ignoreEntities[i]))
                return false;
        }
        return true;
    }

    public boolean heuristicE11_E12(String text) {
        StanfordCoreNLP pipeline = getInstance();
        Annotation doc = new Annotation(text);
        pipeline.annotate(doc);
        List<CoreMap> sentences = doc.get(CoreAnnotations.SentencesAnnotation.class);
        for (CoreMap map : sentences) {
            for (CoreLabel token : map.get(CoreAnnotations.TokensAnnotation.class)) {
                String ner = token.get(CoreAnnotations.NamedEntityTagAnnotation.class);
                if (ner.equalsIgnoreCase("LOCATION") || ner.equalsIgnoreCase("PERSON")
                        || ner.equalsIgnoreCase("DATE") || ner.equalsIgnoreCase("TIME"))
                    return false;
            }
        }
        return true;


    }


//      ATTRIBUTE HEURISTICS


    /*
     *   (RULE HEAD) Adjective
     *   (RULE TAIL) Attribute
     */
    public boolean heuristicA1(String text) {
        StanfordCoreNLP pipeline = getInstance();
        Annotation doc = new Annotation(text);
        pipeline.annotate(doc);
        List<CoreMap> sentences = doc.get(CoreAnnotations.SentencesAnnotation.class);
        for (CoreMap map : sentences) {
            for (CoreLabel token : map.get(CoreAnnotations.TokensAnnotation.class)) {
                String word = token.get(CoreAnnotations.TextAnnotation.class);
                String pos = token.get(CoreAnnotations.PartOfSpeechAnnotation.class);
                if (pos.contains("JJ"))
                    return true;
            }
        }
        return false;
    }

    /*
     *   (RULE HEAD) Adverb
     *   (RULE TAIL) Attribute of relationship
     */
    public boolean heuristicA2(String text) {
        StanfordCoreNLP pipeline = getInstance();
        Annotation doc = new Annotation(text);
        pipeline.annotate(doc);
        List<CoreMap> sentences = doc.get(CoreAnnotations.SentencesAnnotation.class);
        for (CoreMap map : sentences) {
            for (CoreLabel token : map.get(CoreAnnotations.TokensAnnotation.class)) {
                String word = token.get(CoreAnnotations.TextAnnotation.class);
                String pos = token.get(CoreAnnotations.PartOfSpeechAnnotation.class);
                if (pos.contains("RB"))
                    return true;
            }
        }
        return false;
    }


    //RELATIONSHIP RULES


    /*
     *   (RULE HEAD) Verb
     *   (RULE TAIL) Relationship
     */
    public boolean heuristicR1(String text) {
        StanfordCoreNLP pipeline = getInstance();
        Annotation doc = new Annotation(text);
        pipeline.annotate(doc);
        List<CoreMap> sentences = doc.get(CoreAnnotations.SentencesAnnotation.class);
        for (CoreMap map : sentences) {
            for (CoreLabel token : map.get(CoreAnnotations.TokensAnnotation.class)) {
                String word = token.get(CoreAnnotations.TextAnnotation.class);
                String pos = token.get(CoreAnnotations.PartOfSpeechAnnotation.class);
                if (pos.equals("VB"))
                    return true;
            }
        }
        return false;
    }


    /*
     *   (RULE HEAD) Verb with preposition
     *   (RULE TAIL) Association Relationship
     */
    public boolean heuristicR4(String text) {
        StanfordCoreNLP pipeline = getInstance();
        Annotation doc = new Annotation(text);
        pipeline.annotate(doc);
        List<CoreMap> sentences = doc.get(CoreAnnotations.SentencesAnnotation.class);
        for (CoreMap map : sentences) {
            for (CoreLabel token : map.get(CoreAnnotations.TokensAnnotation.class)) {
                String word = token.get(CoreAnnotations.TextAnnotation.class);
                String pos = token.get(CoreAnnotations.PartOfSpeechAnnotation.class);
                if (pos.equals("CLR"))
                    return true;
            }
        }
        return false;
    }




    public StanfordCoreNLP getInstance() {
        if (pipeline == null) {
            Properties properties = new Properties();
            properties.setProperty("annotators", "tokenize, ssplit, pos, lemma, ner, parse, depparse, dcoref");
            pipeline = new StanfordCoreNLP(properties);
        }
        return pipeline;
    }

//    public void getTree(Annotation document){
//        List<CoreMap> sentences = document.get(CoreAnnotations.SentencesAnnotation.class);
//        for (CoreMap map : sentences){
//            Tree tree = map.get(TreeCoreAnnotations.TreeAnnotation.class);
//            List<Tree> leaves = new ArrayList<>();
//            leaves = tree.getLeaves(leaves);
//            for (Tree leaf : leaves){
//
//            }
//        }
//    }

}