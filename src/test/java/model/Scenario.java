package model;

import edu.stanford.nlp.ie.util.RelationTriple;
import edu.stanford.nlp.trees.TypedDependency;
import edu.stanford.nlp.util.CoreMap;

import java.util.ArrayList;
import java.util.Collection;
import java.util.HashSet;
import java.util.List;

public class Scenario {

    private String role;
    private String text;
    private String strippedText;
    private List<String> invalidWords;

    private String subjectElement = "";


    private List<CoreMap> sentenceAnnotation;
    private Collection<RelationTriple> triples;

    private HashSet<String> foundClassList;
    private HashSet<String> notFoundClassList;

    private HashSet<String> foundAttributeList;
    private HashSet<String> notFoundAttributeList;

    private HashSet<String> foundMethodList;
    private HashSet<String> notFoundMethodList;

    private HashSet<String> foundRelationshipList;
    private HashSet<String> notFoundRelationshipList;

    private List<TypedDependency> dependencies;
    private HashSet<TypedDependency> compoundDependencies;
    private HashSet<TypedDependency> amodDependencies;
    private HashSet<TypedDependency> possDependencies;
    private HashSet<TypedDependency> byDependencies;

    public Scenario(String text) {
        this.text = text;
        setupTextVersions(text);

        this.foundClassList = new HashSet<>();
        this.notFoundClassList = new HashSet<>();
        this.foundAttributeList = new HashSet<>();
        this.notFoundAttributeList = new HashSet<>();
        this.foundMethodList = new HashSet<>();
        this.notFoundMethodList = new HashSet<>();
        this.foundRelationshipList = new HashSet<>();
        this.notFoundRelationshipList = new HashSet<>();
        this.invalidWords = new ArrayList<>();


        this.compoundDependencies = new HashSet<>();
        this.amodDependencies = new HashSet<>();
        this.possDependencies = new HashSet<>();
        this.byDependencies = new HashSet<>();

    }


    public List<String> getInvalidWords() {
        return invalidWords;
    }

    public String getSubjectElement() {
        return subjectElement;
    }

    public String getText() {
        return text;
    }

    public String getRole() {
        return role;
    }

    public void setRole(String role) {
        this.role = role;
    }


    public void setSentenceAnnotation(List<CoreMap> sentenceAnnotation) {
        this.sentenceAnnotation = sentenceAnnotation;
    }


    public Collection<RelationTriple> getTriples() {
        return triples;
    }

    public void setTriples(Collection<RelationTriple> triples) {
        this.triples = triples;
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

    @Override
    public String toString() {
        return "Scenario{" +
                "role='" + role + '\'' +
                ", text='" + text + '\'' +
                ", strippedText='" + strippedText + '\'' +
                ", invalidWords=" + invalidWords +
                ", subjectElement='" + subjectElement + '\'' +
                ", sentenceAnnotation=" + sentenceAnnotation +
                ", triples=" + triples +
                ", foundClassList=" + foundClassList +
                ", notFoundClassList=" + notFoundClassList +
                ", foundAttributeList=" + foundAttributeList +
                ", notFoundAttributeList=" + notFoundAttributeList +
                ", foundMethodList=" + foundMethodList +
                ", notFoundMethodList=" + notFoundMethodList +
                ", foundRelationshipList=" + foundRelationshipList +
                ", notFoundRelationshipList=" + notFoundRelationshipList +
                ", dependencies=" + dependencies +
                ", compoundDependencies=" + compoundDependencies +
                ", amodDependencies=" + amodDependencies +
                ", possDependencies=" + possDependencies +
                ", byDependencies=" + byDependencies +
                '}';
    }

    public void setupTextVersions(String text) {
        this.strippedText = text.replaceAll("[^a-zA-Z0-9]", "");
    }


    public void addToFoundClassList(String text) {
        this.foundClassList.add(text);
    }

    public void addToNotFoundClassList(String text) {
        this.notFoundClassList.add(text);
    }

    public void addToFoundAttributeList(String text) {
        this.foundAttributeList.add(text);
    }

    public void addToNotFoundAttributeList(String text) {
        this.notFoundAttributeList.add(text);
    }

    public void addToFoundMethodList(String text) {
        this.foundMethodList.add(text);
    }

    public void addToNotFoundMethodList(String text) {
        this.notFoundMethodList.add(text);
    }

    public void addToFoundRelationshipList(String text) {
        this.foundRelationshipList.add(text);
    }

    public void addToNotFoundRelationshipList(String text) {
        this.notFoundRelationshipList.add(text);
    }

    public void setDependencies(List<TypedDependency> dependencies) {
        this.dependencies = dependencies;
    }

    public List<TypedDependency> getDependencies() {
        return dependencies;
    }

    public void addToCompoundDependencies(TypedDependency typedDependency) {
        this.compoundDependencies.add(typedDependency);
    }

    public void addToAmodDependencies(TypedDependency typedDependency) {
        this.amodDependencies.add(typedDependency);
    }

    public HashSet<TypedDependency> getCompoundDependencies() {
        return compoundDependencies;
    }

    public HashSet<TypedDependency> getAmodDependencies() {
        return amodDependencies;
    }

    public HashSet<TypedDependency> getPossDependencies() {
        return possDependencies;
    }

    public void addToPossDependencies(TypedDependency structure) {
        this.possDependencies.add(structure);
    }

    public void addToByDependencies(TypedDependency structure) {
        this.byDependencies.add(structure);
    }

    public void addInvalidWord(String word) {
        this.invalidWords.add(word);
    }
}
