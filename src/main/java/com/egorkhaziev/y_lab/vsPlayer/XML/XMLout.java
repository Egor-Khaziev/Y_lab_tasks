package com.egorkhaziev.y_lab.vsPlayer.XML;


import javax.xml.parsers.DocumentBuilder;
import javax.xml.parsers.DocumentBuilderFactory;
import javax.xml.transform.Transformer;
import javax.xml.transform.TransformerFactory;
import javax.xml.transform.dom.DOMSource;
import javax.xml.transform.stream.StreamResult;

import org.w3c.dom.Document;
import org.w3c.dom.Element;

import java.io.FileOutputStream;


public class XMLout {

    public static Document createDocument() {
        DocumentBuilderFactory docBuildFactory = DocumentBuilderFactory.newInstance();
        DocumentBuilder documentBuilder = null;
        try {
            documentBuilder = docBuildFactory.newDocumentBuilder();
        } catch (Exception e) {
            e.printStackTrace();
        }
        Document doc = documentBuilder.newDocument();
        return doc;
    }

    public static Element createXMLStep(Document doc, String playerName, int x, int y){
        Element xmlStep = doc.createElement("step");
        xmlStep.setAttribute("x", String.valueOf(x));
        xmlStep.setAttribute("y", String.valueOf(y));
        return xmlStep;
    }

    public static Element createWin(Document doc, String winner){
        Element win = doc.createElement("win");
        win.setAttribute("winner", winner);
        return win;
    }

    public static Element createDetails(Document doc, String first, String second){
        Element details = doc.createElement("details");
        details.setAttribute("first", first);
        details.setAttribute("second", second);
        return details;
    }

    public static void saveAsFile(Document document, String fileName) {
        try {
            Transformer transformer = TransformerFactory.newInstance().newTransformer();
            transformer.setOutputProperty("indent", "yes");
            DOMSource source = new DOMSource(document);
            FileOutputStream fos = new FileOutputStream(fileName);
            StreamResult result = new StreamResult(fos);
            transformer.transform(source, result);
        } catch (Exception ex) {
            ex.printStackTrace();
        }

    }

}
