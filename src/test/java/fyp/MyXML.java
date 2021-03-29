package fyp;

import org.jdom2.Attribute;
import org.jdom2.Document;
import org.jdom2.Element;
import org.jdom2.JDOMException;
import org.jdom2.filter.ElementFilter;
import org.jdom2.input.SAXBuilder;

import java.io.File;
import java.io.IOException;
import java.util.Iterator;
import java.util.List;

public class MyXML {

    private File[] fileList;

    public MyXML() {
        run();
    }

    public void run() {
//        checkClassNodes("Ticket");
//        checkAttributeNodes("R", "Ticket");
//        checkMethodNodes("R", "Ticket");
    }


    public int checkClassNodes(String classToFind) {
        File xml = new File("src/test/resources/classmodels/diagramproject.xml");
        SAXBuilder builder = new SAXBuilder();
        Document doc = new Document();
        try {
            doc = builder.build(xml);
        } catch (JDOMException e) {
            e.printStackTrace();
        } catch (IOException e) {
            e.printStackTrace();
        }

        Iterator<?> processDescendants = doc.getDescendants(new ElementFilter());
        while (processDescendants.hasNext()) {
            Element e = (Element) processDescendants.next();
            String current = e.getName();
            if (current.equals("packagedElement")) {
                List<Attribute> attributeList = e.getAttributes();
                if (attributeList != null) {
                    for (Attribute a : attributeList) {
                        if (a.getValue().equals("uml:Class")) {
                            if (classToFind.equalsIgnoreCase(e.getAttribute("name").getValue())) {
                                return 1;
                            }
                        }
                    }
                }
            }

        }
        return 0;
    }

    public int checkAttributeNodes(String attributeToFind) {
        File xml = new File("src/test/resources/classmodels/diagramproject.xml");
        SAXBuilder builder = new SAXBuilder();
        Document doc = new Document();
        try {
            doc = builder.build(xml);
        } catch (JDOMException e) {
            e.printStackTrace();
        } catch (IOException e) {
            e.printStackTrace();
        }

        Iterator<?> processDescendants = doc.getDescendants(new ElementFilter());
        while (processDescendants.hasNext()) {
            Element e = (Element) processDescendants.next();
            String current = e.getName();
            if (current.equals("ownedAttribute")) {
                Element classElement = e.getParentElement();
//                System.out.println(e.getAttribute("name").getValue() + " attr of " + classElement.getAttribute("name").getValue());
                if (attributeToFind.equalsIgnoreCase(e.getAttribute("name").getValue())) {
                    return 1;
                }
            }
        }
        return 0;
    }

    public int checkAttributeNodes(String attributeToFind, String className) {
        File xml = new File("src/test/resources/classmodels/diagramproject.xml");
        SAXBuilder builder = new SAXBuilder();
        Document doc = new Document();
        try {
            doc = builder.build(xml);
        } catch (JDOMException e) {
            e.printStackTrace();
        } catch (IOException e) {
            e.printStackTrace();
        }

        Iterator<?> processDescendants = doc.getDescendants(new ElementFilter());
        while (processDescendants.hasNext()) {
            Element e = (Element) processDescendants.next();
            String current = e.getName();
            if (current.equals("ownedAttribute")) {
                Element classElement = e.getParentElement();
//               System.out.println(e.getAttribute("name").getValue() + " attr of " + classElement.getAttribute("name").getValue());
                if (attributeToFind.equalsIgnoreCase(e.getAttribute("name").getValue())
                        && className.equalsIgnoreCase(classElement.getAttribute("name").getValue())) {
                    return 1;
                }
            }
        }
        return 0;
    }

    public int checkMethodNodes(String methodToFind) {
        File xml = new File("src/test/resources/classmodels/diagramproject.xml");
        SAXBuilder builder = new SAXBuilder();
        Document doc = new Document();
        try {
            doc = builder.build(xml);
        } catch (JDOMException e) {
            e.printStackTrace();
        } catch (IOException e) {
            e.printStackTrace();
        }

        Iterator<?> processDescendants = doc.getDescendants(new ElementFilter());
        while (processDescendants.hasNext()) {
            Element e = (Element) processDescendants.next();
            String current = e.getName();
            if (current.equals("ownedOperation")) {
                Element classElement = e.getParentElement();
//                System.out.println(e.getAttribute("name").getValue() + " method of " + classElement.getAttribute("name").getValue());
                if (methodToFind.equalsIgnoreCase(e.getAttribute("name").getValue()) || e.getAttribute("name").getValue().contains(methodToFind)) {
                    return 1;
                }
            }
        }
        return 0;
    }

    public int checkMethodNodes(String methodToFind, String className) {
        File xml = new File("src/test/resources/classmodels/diagramproject.xml");
        SAXBuilder builder = new SAXBuilder();
        Document doc = new Document();
        try {
            doc = builder.build(xml);
        } catch (JDOMException e) {
            e.printStackTrace();
        } catch (IOException e) {
            e.printStackTrace();
        }

        Iterator<?> processDescendants = doc.getDescendants(new ElementFilter());
        while (processDescendants.hasNext()) {
            Element e = (Element) processDescendants.next();
            String current = e.getName();
            if (current.equals("ownedOperation")) {
                Element classElement = e.getParentElement();
//                System.out.println(e.getAttribute("name").getValue() + " method of " + classElement.getAttribute("name").getValue());
                if (methodToFind.equalsIgnoreCase(e.getAttribute("name").getValue())
                        && className.equalsIgnoreCase(classElement.getAttribute("name").getValue())) {
                    return 1;
                }
            }
        }
        return 0;
    }


}
