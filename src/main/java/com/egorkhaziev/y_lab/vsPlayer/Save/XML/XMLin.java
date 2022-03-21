package com.egorkhaziev.y_lab.vsPlayer.Save.XML;

import com.egorkhaziev.y_lab.vsPlayer.Save.Model.GamePlay;
import com.egorkhaziev.y_lab.vsPlayer.Save.Model.Step;
import com.egorkhaziev.y_lab.vsPlayer.Save.ReadSaveGame;
import com.egorkhaziev.y_lab.vsPlayer.model.PlayerGame;
import org.w3c.dom.Document;
import org.w3c.dom.Element;
import org.w3c.dom.Node;
import org.w3c.dom.NodeList;

import javax.xml.parsers.DocumentBuilder;
import javax.xml.parsers.DocumentBuilderFactory;
import java.io.File;
import java.util.ArrayList;
import java.util.List;

public class XMLin implements ReadSaveGame {

    List<Step> steps;
    List<PlayerGame> playerGames;
    PlayerGame winner;

    GamePlay gamePlay;

    @Override
    public GamePlay readFile(String fileName) throws Exception {

        gamePlay = new GamePlay();
        steps = new ArrayList<>();
        playerGames = new ArrayList<>();
        winner = null;



        DocumentBuilderFactory factory = DocumentBuilderFactory.newInstance();
        DocumentBuilder builder = factory.newDocumentBuilder();
        Document document = builder.parse(new File(fileName));


        document.getDocumentElement().normalize();
        NodeList playerList = document.getElementsByTagName("PlayerGame");
        NodeList stepList = document.getElementsByTagName("Step");
        NodeList GameResult = document.getElementsByTagName("GameResult");


        //Игроки
        for (int i = 0; i < playerList.getLength(); i++) {

            Node node = playerList.item(i);
            if (node.getNodeType() == Node.ELEMENT_NODE) {
                Element ePlayer = (Element) node;

                PlayerGame playerGame = new PlayerGame();
                playerGame.setName(ePlayer.getAttribute("name"));
                playerGame.setId(Integer.parseInt(ePlayer.getAttribute("id")));

                playerGames.add(playerGame);
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

        //победитель
        if (GameResult.getLength() > 0) {

            if(playerGames.size()==3){
                winner = playerGames.get(2);
            }

        }

        gamePlay.setPlayerGames(playerGames);
        gamePlay.getGame().setSteps(steps);
        gamePlay.getGameResult().setWinner(winner);

        return gamePlay;
    }
}






