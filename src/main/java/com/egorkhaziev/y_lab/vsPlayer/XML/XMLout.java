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

    public static Element createXMLPlayer(Document doc, int id, String name, String symbol) {
        Element player = doc.createElement("Player");
        player.setAttribute("id", String.valueOf(id));
        player.setAttribute("name", name);
        player.setAttribute("symbol", symbol);
        return player;
    }

    public static Element createXMLStep(Document doc,int num, int playerId, int x, int y){
        Element xmlStep = doc.createElement("Step");
        xmlStep.setAttribute("num", String.valueOf(num));
        xmlStep.setAttribute("playerId", String.valueOf(playerId));
        xmlStep.setTextContent(x+""+y);
        return xmlStep;
    }

    public static Element createGameResult(Document doc){
        Element gameResult = doc.createElement("GameResult");
        gameResult.setTextContent("Draw!");
        return gameResult;
    }

    public static Element createGameResult(Document doc, Element winner){
        Element gameResult = doc.createElement("GameResult");
        gameResult.appendChild(winner);
        return gameResult;
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
