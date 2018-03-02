package pers.yicong.foodvenger.xmlParser;

import org.w3c.dom.Document;
import org.w3c.dom.Element;
import org.w3c.dom.NodeList;
import org.xml.sax.SAXException;

import javax.xml.parsers.DocumentBuilder;
import javax.xml.parsers.DocumentBuilderFactory;
import javax.xml.parsers.ParserConfigurationException;
import java.io.IOException;
import java.util.HashSet;
import java.util.Iterator;
import java.util.Set;

public abstract class DomParser<Type> {

    private Set<Type> objects;
    private Document document;

    protected DomParser(String fileName) {

        objects = new HashSet<>();

        try {
            DocumentBuilderFactory dbf = DocumentBuilderFactory.newInstance();
            DocumentBuilder db = dbf.newDocumentBuilder();
            document = db.parse(fileName);


            Element docEle = document.getDocumentElement();

            //get a nodelist of <employee> elements
            NodeList nl = docEle.getElementsByTagName("row");
            if (nl != null && nl.getLength() > 0) {
                for (int i = 0; i < nl.getLength(); i++) {
                    objects.add(getObject((Element) nl.item(i)));
                }
            }
        } catch (ParserConfigurationException | SAXException | IOException e) {
            e.printStackTrace();
        }

        //parse using builder to get DOM representation of the XML file

    }

    public Set<Type> getObjects() {
        return objects;
    }

    public void setObjects(Set<Type> objects) {
        this.objects = objects;
    }

    protected abstract Type getObject(Element empEl);


    protected String getTextValue(Element ele, String tagName) {
        String textVal = null;
        NodeList nl = ele.getElementsByTagName(tagName);
        if (nl != null && nl.getLength() > 0) {
            Element el = (Element) nl.item(0);
            textVal = el.getFirstChild().getNodeValue();
        }
        return textVal;
    }

    protected float getFloatValue(Element ele, String tagName) {
        return Float.parseFloat(getTextValue(ele, tagName));
    }

    /**
     * Calls getTextValue and returns a int value
     *
     * @param ele
     * @param tagName
     * @return
     */
    protected int getIntValue(Element ele, String tagName) {
        //in production application you would catch the exception
        return Integer.parseInt(getTextValue(ele, tagName));
    }

    /**
     * Iterate through the list and print the
     * content to console
     */
    private void printData() {

        System.out.println("No of Employees '" + objects.size() + "'.");

        Iterator<Type> it = objects.iterator();
        while (it.hasNext()) {
            System.out.println(it.next().toString());
        }
    }

}
