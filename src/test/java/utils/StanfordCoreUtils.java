package utils;

import edu.stanford.nlp.ie.util.RelationTriple;
import edu.stanford.nlp.ling.CoreAnnotations;
import edu.stanford.nlp.ling.CoreLabel;
import edu.stanford.nlp.ling.HasWord;
import edu.stanford.nlp.ling.TaggedWord;
import edu.stanford.nlp.naturalli.NaturalLogicAnnotations;
import edu.stanford.nlp.parser.nndep.DependencyParser;
import edu.stanford.nlp.pipeline.Annotation;
import edu.stanford.nlp.pipeline.StanfordCoreNLP;
import edu.stanford.nlp.process.DocumentPreprocessor;
import edu.stanford.nlp.tagger.maxent.MaxentTagger;
import edu.stanford.nlp.trees.GrammaticalStructure;
import edu.stanford.nlp.trees.TypedDependency;
import edu.stanford.nlp.util.CoreMap;

import java.io.StringReader;
import java.util.ArrayList;
import java.util.Collection;
import java.util.List;
import java.util.Properties;

public class StanfordCoreUtils {

    private static StanfordCoreNLP pipeline;

    public StanfordCoreUtils() {
        pipeline = getInstance();
    }


    public List<CoreMap> generateSentenceAnnotation(String text) {
        Annotation annotation = new Annotation(text);
        pipeline.annotate(annotation);
        return annotation.get(CoreAnnotations.SentencesAnnotation.class);
    }


    public Collection<RelationTriple> getTriples(List<CoreMap> sentences) {
        for (CoreMap sentence : sentences) {
            // Get the OpenIE triples for the sentence
            return sentence.get(NaturalLogicAnnotations.RelationTriplesAnnotation.class);

        }
        return null;
    }


    public List<CoreMap> getSentenceAnnotations(String text) {
        Annotation doc = new Annotation(text);
        pipeline.annotate(doc);
        return doc.get(CoreAnnotations.SentencesAnnotation.class);
    }


    public String getLemma(String word) {
        Annotation doc = new Annotation(word);
        pipeline.annotate(doc);
        List<CoreMap> sentences = doc.get(CoreAnnotations.SentencesAnnotation.class);
        for (CoreMap map : sentences) {
            for (CoreLabel token : map.get(CoreAnnotations.TokensAnnotation.class)) {
                return token.get(CoreAnnotations.LemmaAnnotation.class);
            }
        }
        return word;
    }

    public List<TypedDependency> getDependencies(String text) {
        MaxentTagger tagger = new MaxentTagger("edu/stanford/nlp/models/pos-tagger/english-left3words-distsim.tagger");
        DependencyParser parser = DependencyParser.loadFromModelFile(DependencyParser.DEFAULT_MODEL);
        List<TypedDependency> structures = new ArrayList<>();
        DocumentPreprocessor tokenizer = new DocumentPreprocessor(new StringReader(text));
        for (List<HasWord> sentence : tokenizer) {
            List<TaggedWord> tagged = tagger.tagSentence(sentence);
            GrammaticalStructure grammaticalStructure = parser.predict(tagged);
            structures.addAll(grammaticalStructure.typedDependenciesEnhanced());
        }
        return structures;
    }

    public boolean isProperNoun(String text) {
        Annotation doc = new Annotation(text);
        pipeline.annotate(doc);
        List<CoreMap> sentences = doc.get(CoreAnnotations.SentencesAnnotation.class);
        for (CoreMap map : sentences) {
            for (CoreLabel token : map.get(CoreAnnotations.TokensAnnotation.class)) {
                String pos = token.get(CoreAnnotations.PartOfSpeechAnnotation.class);
//                PROPER NOUN = IGNORE AS CLASS
                if (pos.equalsIgnoreCase("NNP")) {
                    return true;
                }
            }
        }
        return false;
    }


    public boolean ignoreNerEntityAnnotations(String word) {
        Annotation doc = new Annotation(word);
        pipeline.annotate(doc);
        List<CoreMap> sentences = doc.get(CoreAnnotations.SentencesAnnotation.class);
        for (CoreMap map : sentences) {
            for (CoreLabel token : map.get(CoreAnnotations.TokensAnnotation.class)) {
                String ner = token.get(CoreAnnotations.NamedEntityTagAnnotation.class);
                if (ner.equalsIgnoreCase("LOCATION") || ner.equalsIgnoreCase("CITY") ||
                        ner.equalsIgnoreCase("COUNTRY") || ner.equalsIgnoreCase("PERSON")
                        || ner.equalsIgnoreCase("DATE") || ner.equalsIgnoreCase("TIME")
                        || ner.equalsIgnoreCase("DURATION") || ner.equalsIgnoreCase("NATIONALITY")
                        || ner.equalsIgnoreCase("STATE_OR_PROVINCE") || ner.equalsIgnoreCase("URL")) {
                    return false;
                }
            }
        }
        return true;
    }


    public StanfordCoreNLP getInstance() {
        if (pipeline == null) {
            Properties properties = new Properties();
            properties.setProperty("annotators", "tokenize,ssplit,pos,lemma,ner,depparse,natlog,openie");
            properties.setProperty("ner.model", "edu/stanford/nlp/models/ner/english.muc.7class.distsim.crf.ser.gz");

            pipeline = new StanfordCoreNLP(properties);
        }
        return pipeline;
    }
}
