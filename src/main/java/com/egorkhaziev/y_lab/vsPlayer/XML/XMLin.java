package com.egorkhaziev.y_lab.vsPlayer.XML;

import com.egorkhaziev.y_lab.menu.GameMenu;
import org.w3c.dom.Document;
import org.w3c.dom.Element;
import org.w3c.dom.Node;
import org.w3c.dom.NodeList;
import org.xml.sax.SAXException;
import javax.xml.parsers.DocumentBuilder;
import javax.xml.parsers.DocumentBuilderFactory;
import javax.xml.parsers.ParserConfigurationException;
import java.io.File;
import java.io.IOException;
import java.util.ArrayList;

import static java.lang.Thread.sleep;


public class XMLin {

    private static ArrayList<Step> steps;
    private static String winner;
    private static XMLDetails details;

    private final char EMPTY_DOT = '*';
    private static final char X_DOT = 'X';
    private static final char O_DOT = 'O';

    private char[][] gameMap;



    public static void readXML(String fileName) throws ParserConfigurationException, IOException, SAXException {
        steps = new ArrayList();
        winner = "no Winner";


        DocumentBuilderFactory factory = DocumentBuilderFactory.newInstance();
        DocumentBuilder builder = factory.newDocumentBuilder();
        Document document = builder.parse(new File(fileName));


        document.getDocumentElement().normalize();
        NodeList detailsList = document.getElementsByTagName("details");
        NodeList stepList = document.getElementsByTagName("step");
        NodeList winnerList = document.getElementsByTagName("win");


        //кто первый + имена
        Node detailsNode = detailsList.item(0);
        if (detailsNode.getNodeType() == Node.ELEMENT_NODE) {
            Element eDetails = (Element) detailsNode;
            details = new XMLDetails();
            details.setFirstPlayer(eDetails.getAttribute("first"));
            details.setSecondPlayer(eDetails.getAttribute("second"));
        }

        //Шаги
        for (int i = 0; i < stepList.getLength(); i++) {

            Node node = stepList.item(i);
            if (node.getNodeType() == Node.ELEMENT_NODE) {
                Element eStep = (Element) node;

                Step step = new Step();
                step.setX(Integer.parseInt(eStep.getAttribute("x")));
                step.setY(Integer.parseInt(eStep.getAttribute("y")));

                steps.add(step);
            }
        }

        //победитель
        Node winnerNode = winnerList.item(0);
        if (winnerNode.getNodeType() == Node.ELEMENT_NODE) {
            Element eWinner = (Element) winnerNode;
            winner = eWinner.getAttribute("winner");
        }


    }

    public static void play() {
        XMLin xmLin = new XMLin();
        xmLin.initMap();
        xmLin.paintMap();

        for (int i = 0; i <= steps.size()-1; i++) {
//            System.out.println("step "+((i % 2 == 1) ? details.firstPlayer : details.secondPlayer));
            int x = steps.get(i).getX();
            int y = steps.get(i).getY();
            xmLin.gameMap[x - 1][y - 1] = ((i % 2 == 1) ? X_DOT : O_DOT);
            xmLin.paintMap();
            xmLin.sleeping(1000);
        }

        System.out.println("WINNER: " + winner);
        xmLin.sleeping(2500);

        new GameMenu().changeGame();

    }

    private void sleeping(int ms){
        try {
            sleep(ms);
        } catch (InterruptedException e) {
            e.printStackTrace();
        }
    }

    private void initMap() {

        gameMap = new char[3][3];

        for (char[] line : gameMap) {
            for (int i = 0; i < line.length; i++) {
                line[i] = EMPTY_DOT;

            }
        }
    }

    private void paintMap() {
        for (int i = 0; i < gameMap.length; i++) {
            System.out.println();
            System.out.print("|");
            for (int j = 0; j < gameMap[i].length; j++) {
                System.out.print(gameMap[i][j] + "|");
            }
        }
        System.out.println();
    }


}

