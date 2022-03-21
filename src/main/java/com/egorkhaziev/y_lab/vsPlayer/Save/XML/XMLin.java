package com.egorkhaziev.y_lab.vsPlayer.Save.XML;

import com.egorkhaziev.y_lab.vsPlayer.Save.Model.GamePlay;
import com.egorkhaziev.y_lab.vsPlayer.Save.Model.Step;
import com.egorkhaziev.y_lab.vsPlayer.Save.ReadSaveGame;
import com.egorkhaziev.y_lab.vsPlayer.model.Player;
import org.w3c.dom.Document;
import org.w3c.dom.Element;
import org.w3c.dom.Node;
import org.w3c.dom.NodeList;

import javax.xml.parsers.DocumentBuilder;
import javax.xml.parsers.DocumentBuilderFactory;
import java.io.File;
import java.util.List;

public class XMLin implements ReadSaveGame {

    List<Step> steps;
    List<Player> players;

    GamePlay gamePlay;

    @Override
    public GamePlay readFile(String fileName) throws Exception {
        gamePlay = new GamePlay();


        steps = gamePlay.getGame().getSteps();
        players = gamePlay.getPlayers();


        DocumentBuilderFactory factory = DocumentBuilderFactory.newInstance();
        DocumentBuilder builder = factory.newDocumentBuilder();
        Document document = builder.parse(new File(fileName));


        document.getDocumentElement().normalize();
        NodeList playerList = document.getElementsByTagName("Player");
        NodeList stepList = document.getElementsByTagName("Step");
        NodeList GameResult = document.getElementsByTagName("GameResult");


        //Игроки
        for (int i = 0; i < playerList.getLength(); i++) {

            Node node = playerList.item(i);
            if (node.getNodeType() == Node.ELEMENT_NODE) {
                Element ePlayer = (Element) node;

                Player player = new Player();
                player.setName(ePlayer.getAttribute("name"));
                player.setId(Integer.parseInt(ePlayer.getAttribute("id")));

                players.add(player);
            }
        }

        //Шаги
        for (int i = 0; i < stepList.getLength(); i++) {

            Node node = stepList.item(i);
            if (node.getNodeType() == Node.ELEMENT_NODE) {
                Element eStep = (Element) node;

                Step step = new Step();
                String textContentTemp = eStep.getTextContent();
                step.setX(Integer.parseInt(textContentTemp.substring(0, 1)));
                step.setY(Integer.parseInt(textContentTemp.substring(1, 2)));
                step.setPlayerId(Integer.parseInt(eStep.getAttribute("playerId")));
                step.setNum(Integer.parseInt(eStep.getAttribute("num")));

                steps.add(step);
            }
        }

        /** Нужно проверить срабатывание*/
//        //победитель
//        Node winnerNode = GameResult.item(0);
//        if (winnerNode.getNodeType() == Node.ELEMENT_NODE) {
//
//        Element eWinner = (Element) winnerNode;
//
//        winner = new Player();
//        winner.setName(eWinner.getAttribute("name"));
//        winner.setId(Integer.parseInt(eWinner.getAttribute("id")));
//
//        }

        return gamePlay;
    }
}






