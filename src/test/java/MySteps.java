import br.gov.frameworkdemoiselle.behave.parser.Step;
import org.jbehave.core.annotations.*;
import utils.MyParser;

import java.text.SimpleDateFormat;
import java.util.Date;
import java.util.HashSet;

public class MySteps implements Step {

    private MyParser myParser;

    private HashSet<String> foundClassList;
    private HashSet<String> notFoundClassList;
    private HashSet<String> foundAttributeList;
    private HashSet<String> notFoundAttributeList;
    private HashSet<String> foundMethodList;
    private HashSet<String> notFoundMethodList;
    private HashSet<String> foundRelationshipList;
    private HashSet<String> notFoundRelationshipList;
    private HashSet<String> potentialFoundAttributeList;
    private HashSet<String> potentialNotFoundAttributeList;
    private HashSet<String> potentialRelationList;

    private int actualClassCount = 0;
    private int actualAttrCount = 0;
    private int actualMethodCount = 0;
    private int actualRelationshipCount = 0;

    public MySteps() throws Exception {
        myParser = new MyParser();
    }

    @AfterStory
    public void resetRole() {
        myParser.setUserRole("");
        myParser.appendExtractedLists();
    }


    @Given("$local")
    @When("$local")
    @Then("$local")
    public void givenWhenThen(String local) {
        long currentTimeMillisime = System.currentTimeMillis();
        SimpleDateFormat sdf = new SimpleDateFormat("MMM dd,yyyy HH:mm:ss");
        Date resultdate = new Date(currentTimeMillisime);
        System.out.println("----------------------------------------------------------------------------------------------" +
                "-----------------------------------------------------------------------------------------------------");
        System.out.println("[" + sdf.format(resultdate) + "]\t\tANALYSING SCENARIO: " + local);
        System.out.println("----------------------------------------------------------------------------------------------" +
                "-----------------------------------------------------------------------------------------------------");
        myParser.setupScenario(local);
    }

    @AfterStories
    public void printResults() {
        System.out.println("----------------------------------------------------------------------------------------------" +
                "-----------------------------------------------------------------------------------------------------");
        System.out.println("COLLECTING RESULT METRICS");
        System.out.println("----------------------------------------------------------------------------------------------" +
                "-----------------------------------------------------------------------------------------------------");
        collectExtractedElements();
        classStats();
        attrStats();
        methodStats();
        relStats();
        precisionStats();
        recallStats();
        potentialAttrStats();
        potentialRelStats();
        System.out.println("----------------------------------------------------------------------------------------------" +
                "-----------------------------------------------------------------------------------------------------");
    }

    public void potentialAttrStats() {
        System.out.println("\n\nPOTENTIAL ATTRIBUTES (CLASS UNKNOWN)");
        System.out.println("Attributes Identified in text without a known parent class:");
        System.out.println("----------------------------------------------------------------------------------------------" +
                "-----------------------------------------------------------------------------------------------------");
        System.out.format("%25s\t%150s\t%10s", "ATTRIBUTE", "SENTENCE", "FOUND");
        System.out.println("\n----------------------------------------------------------------------------------------------" +
                "-----------------------------------------------------------------------------------------------------");
        for (String element : potentialFoundAttributeList) {
            String[] splitEl = element.split(",");
            if (splitEl.length == 2) {
                System.out.format("%25s\t%150s\t%10s", splitEl[0], splitEl[1], "YES");
                System.out.println("\n----------------------------------------------------------------------------------------------" +
                        "-----------------------------------------------------------------------------------------------------");
            }

        }
        for (String element : potentialNotFoundAttributeList) {
            String[] splitEl = element.split(",");
            if (splitEl.length == 2) {
                System.out.format("%25s\t%150s\t%10s", splitEl[0], splitEl[1], "NO");
                System.out.println("\n----------------------------------------------------------------------------------------------" +
                        "-----------------------------------------------------------------------------------------------------");
            }
        }
    }

    public void potentialRelStats() {
        System.out.println("\n\nPOTENTIAL RELATIONS (CLASS UNKNOWN)");
        System.out.println("Relations Identified by verbs in text without known entities:");
        System.out.println("----------------------------------------------------------------------------------------------" +
                "-----------------------------------------------------------------------------------------------------");
        System.out.format("%15s\t%170s", "RELATION", "SENTENCE");
        System.out.println("\n----------------------------------------------------------------------------------------------" +
                "-----------------------------------------------------------------------------------------------------");
        for (String element : potentialRelationList) {
            String[] splitEl = element.split(",");
            if (splitEl.length == 2) {
                System.out.format("%15s\t%170s", splitEl[0], splitEl[1]);
                System.out.println("\n----------------------------------------------------------------------------------------------" +
                        "-----------------------------------------------------------------------------------------------------");
            }
        }
    }


    public void collectExtractedElements() {

        // Lists collected by Parser
        foundClassList = myParser.getFoundClassList();
        notFoundClassList = myParser.getNotFoundClassList();

        foundAttributeList = myParser.getFoundAttributeList();

        notFoundAttributeList = myParser.getNotFoundAttributeList();

        foundMethodList = myParser.getFoundMethodList();

        notFoundMethodList = myParser.getNotFoundMethodList();

        foundRelationshipList = myParser.getFoundRelationshipList();

        notFoundRelationshipList = myParser.getNotFoundRelationshipList();

        potentialFoundAttributeList = myParser.getPotentialFoundAttributeList();

        potentialNotFoundAttributeList = myParser.getNotPotentialFoundAttributeList();

        potentialRelationList = myParser.getPotentialRelationList();


        // Lists collected from class diagrams
        actualClassCount = myParser.getClassCount();
        actualAttrCount = myParser.getAttrCount();
        actualMethodCount = myParser.getMethodCount();
        actualRelationshipCount = myParser.getRelationshipCount();

    }

    public void precisionStats() {
        int classPrecision = 0;
        int attrPrecision = 0;
        int methodPrecision = 0;
        int relPrecision = 0;

        int classCount = foundClassList.size() + notFoundClassList.size();
        int attrCount = foundAttributeList.size() + notFoundAttributeList.size();
        int methodCount = foundMethodList.size() + notFoundMethodList.size();
        int relCount = foundRelationshipList.size() + notFoundRelationshipList.size();

        if (classCount != 0) {
            classPrecision = foundClassList.size() * 100 / classCount;
        }
        if (attrCount != 0) {
            attrPrecision = foundAttributeList.size() * 100 / attrCount;
        }
        if (methodCount != 0) {
            methodPrecision = foundMethodList.size() * 100 / methodCount;
        }
        if (relCount != 0) {
            relPrecision = foundRelationshipList.size() * 100 / relCount;
        }

        System.out.println("\n\nPRECISION STATS");
        System.out.println("Elements correctly & incorrectly identified in text compared with those in class diagram:");
        System.out.println("----------------------------------------------------------------------------------------------" +
                "-----------------------------------------------------------------------------------------------------");

        System.out.format("%15s\t%15s\t%15s\t%15s", "", "IDENTIFIED", "CORRECT", "PRECISION");
        System.out.println("\n----------------------------------------------------------------------------------------------" +
                "-----------------------------------------------------------------------------------------------------");

        System.out.format("%15s\t%15d\t%15d\t%15s", "Entities", classCount, foundClassList.size(), classPrecision + "%");
        System.out.println("\n----------------------------------------------------------------------------------------------" +
                "-----------------------------------------------------------------------------------------------------");
        System.out.format("%15s\t%15d\t%15d\t%15s", "Attributes", attrCount, foundAttributeList.size(), attrPrecision + "%");
        System.out.println("\n----------------------------------------------------------------------------------------------" +
                "-----------------------------------------------------------------------------------------------------");

        System.out.format("%15s\t%15d\t%15d\t%15s", "Relationships", relCount, foundRelationshipList.size(), relPrecision + "%");
        System.out.println("\n----------------------------------------------------------------------------------------------" +
                "-----------------------------------------------------------------------------------------------------");

        System.out.format("%15s\t%15d\t%15d\t%15s", "Methods", methodCount, foundMethodList.size(), methodPrecision + "%");
        System.out.println("\n----------------------------------------------------------------------------------------------" +
                "-----------------------------------------------------------------------------------------------------");

    }

    public void classStats() {
        System.out.println("\n\nCLASS EXTRACTION STATS");
        System.out.println("Classes correctly & incorrectly identified in text:");
        System.out.println("CORRECT: " + foundClassList.size() + "\tINCORRECT: " + notFoundClassList.size());
        System.out.println("----------------------------------------------------------------------------------------------" +
                "-----------------------------------------------------------------------------------------------------");

        System.out.format("%25s\t%25s", "ELEMENT", "CORRECT");
        System.out.println("\n----------------------------------------------------------------------------------------------" +
                "-----------------------------------------------------------------------------------------------------");

        for (String s : foundClassList) {
            System.out.format("%25s\t%25s", s, "YES");
            System.out.println("\n----------------------------------------------------------------------------------------------" +
                    "-----------------------------------------------------------------------------------------------------");
        }
        for (String s : notFoundClassList) {
            System.out.format("%25s\t%25s", s, "NO");
            System.out.println("\n----------------------------------------------------------------------------------------------" +
                    "-----------------------------------------------------------------------------------------------------");
        }
    }

    public void attrStats() {
        System.out.println("\n\nATTRIBUTE EXTRACTION STATS");
        System.out.println("Attributes correctly & incorrectly identified in text:");
        System.out.println("CORRECT: " + foundAttributeList.size() + "\tINCORRECT: " + notFoundAttributeList.size());
        System.out.println("----------------------------------------------------------------------------------------------" +
                "-----------------------------------------------------------------------------------------------------");

        System.out.format("%25s\t%25s\t%15s", "ELEMENT", "CLASS", "CORRECT");
        System.out.println("\n----------------------------------------------------------------------------------------------" +
                "-----------------------------------------------------------------------------------------------------");

        for (String s : foundAttributeList) {
            String[] splitStr = s.split(",");
            if (splitStr.length == 2) {
                System.out.format("%25s\t%25s\t%15s", splitStr[0], splitStr[1], "YES");
                System.out.println("\n----------------------------------------------------------------------------------------------" +
                        "-----------------------------------------------------------------------------------------------------");
            }
        }
        for (String s : notFoundAttributeList) {
            String[] splitStr = s.split(",");
            if (splitStr.length == 2) {
                System.out.format("%25s\t%25s\t%15s", splitStr[0], splitStr[1], "NO");
                System.out.println("\n----------------------------------------------------------------------------------------------" +
                        "-----------------------------------------------------------------------------------------------------");
            }

        }
    }


    public void methodStats() {
        System.out.println("\n\nMETHOD EXTRACTION STATS");
        System.out.println("Methods correctly & incorrectly identified in text:");
        System.out.println("CORRECT: " + foundMethodList.size() + "\tINCORRECT: " + notFoundMethodList.size());
        System.out.println("----------------------------------------------------------------------------------------------" +
                "-----------------------------------------------------------------------------------------------------");

        System.out.format("%25s\t%25s", "ELEMENT", "CORRECT");
        System.out.println("\n----------------------------------------------------------------------------------------------" +
                "-----------------------------------------------------------------------------------------------------");

        for (String s : foundMethodList) {
            String[] splitStr = s.split(",");
            if (splitStr.length == 2) {
                System.out.format("%25s\t%25s", splitStr[0], "YES");
                System.out.println("\n----------------------------------------------------------------------------------------------" +
                        "-----------------------------------------------------------------------------------------------------");
            }
        }
        for (String s : notFoundMethodList) {
            String[] splitStr = s.split(",");
            if (splitStr.length == 2) {
                System.out.format("%25s\t%25s", splitStr[0], "NO");
                System.out.println("\n----------------------------------------------------------------------------------------------" +
                        "-----------------------------------------------------------------------------------------------------");
            }
        }
    }

    public void relStats() {
        System.out.println("\n\nRELATION EXTRACTION STATS");
        System.out.println("Relationships correctly & incorrectly identified in text:");
        System.out.println("CORRECT: " + foundRelationshipList.size() + "\tINCORRECT: " + notFoundRelationshipList.size());
        System.out.println("----------------------------------------------------------------------------------------------" +
                "-----------------------------------------------------------------------------------------------------");

        System.out.format("%25s\t%25s\t%25s\t%25s", "ELEMENT 1", "ELEMENT 2", "TYPE", "CORRECT");
        System.out.println("\n----------------------------------------------------------------------------------------------" +
                "-----------------------------------------------------------------------------------------------------");

        for (String s : foundRelationshipList) {
            String[] splitStr = s.split(",");
            if (splitStr.length == 2) {
                System.out.format("%25s\t%25s\t%25s\t%25s", splitStr[0], splitStr[1], "", "YES");
                System.out.println("\n----------------------------------------------------------------------------------------------" +
                        "-----------------------------------------------------------------------------------------------------");
            } else if (splitStr.length == 3) {
                System.out.format("%25s\t%25s\t%25s\t%25s", splitStr[0], splitStr[1], splitStr[2], "YES");
                System.out.println("\n----------------------------------------------------------------------------------------------" +
                        "-----------------------------------------------------------------------------------------------------");
            }
        }
        for (String s : notFoundRelationshipList) {
            String[] splitStr = s.split(",");
            if (splitStr.length == 2) {
                System.out.format("%25s\t%25s\t%25s\t%25s", splitStr[0], splitStr[1], "", "NO");
                System.out.println("\n----------------------------------------------------------------------------------------------" +
                        "-----------------------------------------------------------------------------------------------------");
            } else if (splitStr.length == 3) {
                System.out.format("%25s\t%25s\t%25s\t%25s", splitStr[0], splitStr[1], splitStr[2], "NO");
                System.out.println("\n----------------------------------------------------------------------------------------------" +
                        "-----------------------------------------------------------------------------------------------------");
            }

        }
    }


    public void recallStats() {
        int classRecall = 0;
        int attrRecall = 0;
        int methodRecall = 0;
        int relRecall = 0;

        if (actualClassCount != 0) {
            classRecall = foundClassList.size() * 100 / actualClassCount;
        }
        if (actualAttrCount != 0) {
            attrRecall = foundAttributeList.size() * 100 / actualAttrCount;
        }
        if (actualMethodCount != 0) {
            methodRecall = foundMethodList.size() * 100 / actualMethodCount;
        }
        if (actualRelationshipCount != 0) {
            relRecall = foundRelationshipList.size() * 100 / actualRelationshipCount;
        }

        System.out.println("\n\nRECALL STATS");
        System.out.println("Elements Identified in text compared with those in class diagram:");
        System.out.println("----------------------------------------------------------------------------------------------" +
                "-----------------------------------------------------------------------------------------------------");

        System.out.format("%15s\t%15s\t%15s\t%15s", "", "IDENTIFIED", "CORRECT", "RECALL");
        System.out.println("\n----------------------------------------------------------------------------------------------" +
                "-----------------------------------------------------------------------------------------------------");

        System.out.format("%15s\t%15d\t%15d\t%15s", "Entities", actualClassCount, foundClassList.size(), classRecall + "%");
        System.out.println("\n----------------------------------------------------------------------------------------------" +
                "-----------------------------------------------------------------------------------------------------");
        System.out.format("%15s\t%15d\t%15d\t%15s", "Attributes", actualAttrCount, foundAttributeList.size(), attrRecall + "%");
        System.out.println("\n----------------------------------------------------------------------------------------------" +
                "-----------------------------------------------------------------------------------------------------");

        System.out.format("%15s\t%15d\t%15d\t%15s", "Relationships", actualRelationshipCount, foundRelationshipList.size(), relRecall + "%");
        System.out.println("\n----------------------------------------------------------------------------------------------" +
                "-----------------------------------------------------------------------------------------------------");

        System.out.format("%15s\t%15d\t%15d\t%15s", "Methods", actualMethodCount, foundMethodList.size(), methodRecall + "%");
        System.out.println("\n----------------------------------------------------------------------------------------------" +
                "-----------------------------------------------------------------------------------------------------");
    }


}
