package utils;

import java.io.File;
import java.io.FileNotFoundException;
import java.util.HashSet;
import java.util.Scanner;
import java.util.regex.Matcher;
import java.util.regex.Pattern;

public class SearchModel {

    private static final String METHOD_REGEX = "^.*[\\(\\)].*$";
    private static final String MODEL_FOLDER = "src/test/resources/classmodels";
    private static final String EXTENSION = "Extension";
    public static final String COMPOSITION = "Composition";
    public static final String AGGREGATION = "Aggregation";
    public static final String ASSOCIATION = "Association";


    private HashSet<String> totalClassCount;
    private HashSet<String> totalAttrCount;
    private HashSet<String> totalMethodCount;
    private HashSet<String> totalRelationshipCount;
    private File folder;
    File[] models;

    private String currentClass = "";

    public SearchModel() {
        totalClassCount = new HashSet<>();
        totalAttrCount = new HashSet<>();
        totalMethodCount = new HashSet<>();
        totalRelationshipCount = new HashSet<>();
        folder = new File(MODEL_FOLDER);
        models = folder.listFiles();
    }

    public int checkMethod(String name) {
        for (File file : models) {
            if (file.isFile()) {
                File model = new File(MODEL_FOLDER + "/" + file.getName());
                try {
                    Scanner scanner = new Scanner(model);
                    Pattern pattern = Pattern.compile(METHOD_REGEX);
                    Matcher matcher;
                    while (scanner.hasNext()) {
                        String line = scanner.nextLine();
                        matcher = pattern.matcher(line);
                        if (!line.contains("@") && !line.contains("{") && !line.contains("}") && matcher.find()) {

                            String[] splitStr = line.trim().split("\\(");
                            if (splitStr.length > 0) {

                                if (splitStr[0].toLowerCase().equals(name.toLowerCase())) {
                                    return 1;
                                }
                            }
                        }
                    }

                } catch (FileNotFoundException e) {
                    e.printStackTrace();
                }
            }
        }
        return 0;
    }

    public int checkMethod(String name, String className) {
        String activeClass = "";
        for (File file : models) {

            if (file.isFile()) {
                File model = new File(MODEL_FOLDER + "/" + file.getName());

                try {
                    Scanner scanner = new Scanner(model);
                    Pattern pattern = Pattern.compile(METHOD_REGEX);
                    Matcher matcher;
                    while (scanner.hasNext()) {
                        String line = scanner.nextLine();
                        matcher = pattern.matcher(line);
                        if (line.toLowerCase().contains("class") && line.contains("{")) {
                            String[] words = line.trim().split(" ");
                            if (words.length > 1) {
                                activeClass = words[1];
                            }
                        } else if (!line.contains("@") && !line.contains("{") && !line.contains("}") && matcher.find()) {
                            String[] splitStr = line.trim().split("\\(");
                            if (splitStr.length > 0) {

                                if (splitStr[0].toLowerCase().equals(name.toLowerCase())
                                        && activeClass.toLowerCase().equals(className.toLowerCase())) {
                                    return 1;
                                }
                            }
                        }
                    }

                } catch (FileNotFoundException e) {
                    e.printStackTrace();
                }
            }
        }
        return 0;
    }


    public int searchClass(String name) {
        File folder = new File(MODEL_FOLDER);
        File[] models = folder.listFiles();
        for (File file : models) {
            if (file.isFile()) {
                File model = new File(MODEL_FOLDER + "/" + file.getName());

                try {
                    Scanner scanner = new Scanner(model);
                    while (scanner.hasNext()) {
                        String line = scanner.nextLine();
                        if (line.toLowerCase().contains("class") && line.contains("{")) {
                            String[] words = line.trim().split(" ");
                            if (words.length > 1) {
                                String className = words[1];
                                if (name.toLowerCase().equals(className.toLowerCase())) {
                                    return 1;
                                }
                            }
                        }
                    }

                } catch (FileNotFoundException e) {
                    e.printStackTrace();
                }


            }
        }


        return 0;
    }

    public int checkAttribute(String name, String className) {
        String activeClass = "";
        for (File file : models) {
            if (file.isFile()) {
                File model = new File(MODEL_FOLDER + "/" + file.getName());

                try {
                    Scanner scanner = new Scanner(model);
                    while (scanner.hasNext()) {
                        String line = scanner.nextLine();
                        if (line.toLowerCase().contains("class") && line.contains("{")) {
                            String[] words = line.trim().split(" ");
                            if (words.length > 1) {
                                activeClass = words[1];
                            }
                        } else if (!line.contains("@") && !line.contains("{") && !line.contains("}")
                                && !line.contains("(") && !line.contains(")") && !line.isEmpty()) {
                            String[] words = line.trim().split(" ");
                            if (words.length > 1) {
                                boolean found = activeClass.toLowerCase().equals(className.toLowerCase())
                                        && words[1].toLowerCase().equals(name.toLowerCase());
                                if (found) {
                                    return 1;
                                }
                            }
                        }
                    }
                } catch (FileNotFoundException e) {
                    e.printStackTrace();
                }
            }
        }
        return 0;
    }


    public int checkAttribute(String name) {
        for (File file : models) {
            if (file.isFile()) {
                File model = new File(MODEL_FOLDER + "/" + file.getName());

                try {
                    Scanner scanner = new Scanner(model);
                    while (scanner.hasNext()) {
                        String line = scanner.nextLine();
                        if (!line.contains("@") && !line.contains("{") && !line.contains("}")
                                && !line.contains("(") && !line.contains(")") && !line.isEmpty()) {
                            String[] words = line.trim().split(" ");
                            if (words.length > 1) {
                                if (words[1].toLowerCase().equals(name.toLowerCase())) {
                                    return 1;
                                }
                            }
                        }
                    }

                } catch (FileNotFoundException e) {
                    e.printStackTrace();
                }
            }
        }
        return 0;
    }


    public int getRelationships(String element1, String element2) {
        for (File file : models) {
            if (file.isFile()) {
                File model = new File(MODEL_FOLDER + "/" + file.getName());

                try {
                    Scanner scanner = new Scanner(model);
                    while (scanner.hasNext()) {
                        String line = scanner.nextLine();
//                        System.out.println(line + " OOEAIJD");
                        String temp = line.replaceAll("\\.\\.", "--");
                        if (temp.contains("--") && !temp.contains("@")) {
                            String[] splitStr = temp.trim().split(" ");
                            if (splitStr.length == 3) {

                                String e1 = splitStr[0];
                                String e2 = splitStr[2];

                                if (e1.equalsIgnoreCase(element1) && e2.equalsIgnoreCase(element2)) {
                                    return 1;
                                } else if (e1.equalsIgnoreCase(element2) && e2.equalsIgnoreCase(element1)) {
                                    return 1;
                                }

                            }
                        }

                    }

                } catch (FileNotFoundException e) {
                    e.printStackTrace();
                }
            }
        }
        return 0;
    }


    private String getRelationshipType(String symbol) {
        switch (symbol) {
            case "*--":
                return COMPOSITION;
            case "<|--":
                return EXTENSION;
            case "o--":
                return AGGREGATION;
            case "--":
                return ASSOCIATION;
            case "-->":
                return ASSOCIATION;
            case "--|>":
                return ASSOCIATION;
            case "<--*":
                return ASSOCIATION;
            case "#--":
                return ASSOCIATION;
            case "x--":
                return ASSOCIATION;
            case "}--":
                return ASSOCIATION;
            case "+--":
                return ASSOCIATION;
            case "^--":
                return ASSOCIATION;
            default:
                break;
        }
        return "";
    }

    public int getRelationships(String element1, String element2, String type) {
        for (File file : models) {
            if (file.isFile()) {
                File model = new File(MODEL_FOLDER + "/" + file.getName());

                try {
                    Scanner scanner = new Scanner(model);
                    while (scanner.hasNext()) {
                        String line = scanner.nextLine();
                        if (line.contains("*--") || line.contains("*..") ||
                                line.contains("<|--") || line.contains("<|..") ||
                                line.contains("o--") || line.contains("o..") ||
                                line.contains("..") || line.contains("--") ||
                                line.contains("-->") || line.contains("..>") ||
                                line.contains("..|>") || line.contains("--|>") ||
                                line.contains("<--*") || line.contains("<..*") ||
                                line.contains("#--") || line.contains("#..") ||
                                line.contains("x--") || line.contains("x..") ||
                                line.contains("}--") || line.contains("}..") ||
                                line.contains("+--") || line.contains("+..") ||
                                line.contains("^--") || line.contains("^..")) {


                            String[] splitLine = line.trim().split(" ");
                            String rel = splitLine[1];
                            String relationshipType = getRelationshipType(rel);

                            if (relationshipType.equalsIgnoreCase(type)) {
                                String el1 = splitLine[0];
                                String el2 = splitLine[2];

                                if (element1.replaceAll(" ", "").equalsIgnoreCase(el1)
                                        && element2.replaceAll(" ", "").equalsIgnoreCase(el2)
                                        || element1.replaceAll(" ", "").equalsIgnoreCase(el2)
                                        && element2.replaceAll(" ", "").equalsIgnoreCase(el1)) {
                                    return 1;
                                }
                            }

                        }


                    }

                } catch (FileNotFoundException e) {
                    e.printStackTrace();
                }
            }
        }
        return 0;
    }


    public void collectElements() {
        File folder = new File(MODEL_FOLDER);
        File[] models = folder.listFiles();
        for (File file : models) {
            if (file.isFile()) {
                File model = new File(MODEL_FOLDER + "/" + file.getName());

                try {
                    Scanner scanner = new Scanner(model);
                    Pattern pattern = Pattern.compile(METHOD_REGEX);
                    Matcher matcher;
                    while (scanner.hasNext()) {
                        String line = scanner.nextLine();
                        matcher = pattern.matcher(line);
                        String temp = line.replaceAll("\\.\\.", "--");
                        // GET RELATIONSHIPS
                        if (line.contains("*--") || line.contains("*..") ||
                                line.contains("<|--") || line.contains("<|..") ||
                                line.contains("o--") || line.contains("o..") ||
                                line.contains("..") || line.contains("--") ||
                                line.contains("-->") || line.contains("..>") ||
                                line.contains("..|>") || line.contains("--|>") ||
                                line.contains("<--*") || line.contains("<..*") ||
                                line.contains("#--") || line.contains("#..") ||
                                line.contains("x--") || line.contains("x..") ||
                                line.contains("}--") || line.contains("}..") ||
                                line.contains("+--") || line.contains("+..") ||
                                line.contains("^--") || line.contains("^..")) {

                            String[] splitStr = temp.trim().split(" ");
                            if (splitStr.length == 3) {

                                String e1 = splitStr[0];
                                String e2 = splitStr[2];
                                totalRelationshipCount.add(e1.toLowerCase() + "," + e2.toLowerCase() + "," + splitStr[1].toLowerCase());
                            }
                        } else if (!line.contains("@") && !line.contains("{") && !line.contains("}") && matcher.find()) {

                            String[] splitStr = line.trim().split("\\(");
                            if (splitStr.length > 0) {

                                totalMethodCount.add(splitStr[0] + "," + currentClass.toLowerCase());

                            }
                        } else if (line.toLowerCase().contains("class") && line.contains("{")) {
                            currentClass = "";
                            String[] words = line.trim().split(" ");
                            if (words.length > 1) {
                                String className = words[1];
                                currentClass = words[1];
                                totalClassCount.add(currentClass.toLowerCase());
                            }

                        } else if (!line.contains("@") && !line.contains("{") && !line.contains("}")
                                && !line.contains("(") && !line.contains(")") && !line.trim().isEmpty()) {
                            String[] words = line.trim().split(" ");
                            if (words.length > 1) {
                                totalAttrCount.add(words[1].toLowerCase() + "," + currentClass.toLowerCase());
                            }
                        }
                    }

                } catch (FileNotFoundException e) {
                    e.printStackTrace();
                }
            }
        }
    }


    public HashSet<String> getTotalClassCount() {
        return totalClassCount;
    }

    public HashSet<String> getTotalAttrCount() {
        return totalAttrCount;
    }

    public HashSet<String> getTotalMethodCount() {
        return totalMethodCount;
    }

    public HashSet<String> getTotalRelationshipCount() {
        return totalRelationshipCount;
    }
}
