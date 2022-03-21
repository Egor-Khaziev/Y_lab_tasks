package com.egorkhaziev.y_lab.vsPlayer.Save.XML;


import javax.xml.parsers.DocumentBuilder;
import javax.xml.parsers.DocumentBuilderFactory;
import javax.xml.transform.Transformer;
import javax.xml.transform.TransformerFactory;
import javax.xml.transform.dom.DOMSource;
import javax.xml.transform.stream.StreamResult;

import com.egorkhaziev.y_lab.vsPlayer.Save.Model.GamePlay;
import com.egorkhaziev.y_lab.vsPlayer.Save.Model.Step;
import com.egorkhaziev.y_lab.vsPlayer.Save.WriteSaveGame;
import com.egorkhaziev.y_lab.vsPlayer.model.PlayerGame;
import org.w3c.dom.Document;
import org.w3c.dom.Element;

import java.io.FileOutputStream;


public class XMLout implements WriteSaveGame {

    private final GamePlay gamePlay;

    private Element gamePlayXML;
    private Document document;
    private Element game;

    String fileName;

    private final char X_DOT = 'X';
    private final char O_DOT = 'O';

    public XMLout(GamePlay gamePlay, int gameNumber) {
        this.gamePlay = gamePlay;
        this.fileName = "game-" + gameNumber + ".xml";

        //Создание XML заготовки
        init();
        //сохранение заготовки
        writeSaveGameFile(gamePlay, fileName);
    }

    private void init() {

        document = createDocument();
        gamePlayXML = document.createElement("Gameplay");
        document.appendChild(gamePlayXML);

        //Добавление игроков
        for (PlayerGame playerGame : gamePlay.getPlayerGames()){
            Element playerXML = createPlayer(playerGame);
            gamePlayXML.appendChild(playerXML);
        }

        //добавление подраздела Game
        game = document.createElement("Game");
        gamePlayXML.appendChild(game);

        //XML добавление Step
        for (Step s: gamePlay.getGame().getSteps()) {
            Element step = createStep(s);
            game.appendChild(step);
        }

        //XML победитель
        if(gamePlay.getGameResult().getWinner()==null) {
            Element gameResult = createGameResult();
            gamePlayXML.appendChild(gameResult);
        } else {
            Element winner = createPlayer(gamePlay.getGameResult().getWinner());
            Element gameResult = createGameResult(winner);
            gamePlayXML.appendChild(gameResult);
        }

    }

    private Document createDocument() {
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

    private Element createPlayer( PlayerGame playerGame) {
        Element playerXML = document.createElement("PlayerGame");
        playerXML.setAttribute("id", String.valueOf(playerGame.getId()));
        playerXML.setAttribute("name", playerGame.getName());
        playerXML.setAttribute("symbol", String.valueOf(playerGame.getId()==1?X_DOT:O_DOT));
        return playerXML;
    }

    private Element createStep( Step s){
        Element xmlStep = document.createElement("Step");
        xmlStep.setAttribute("num", String.valueOf(s.getNum()));
        xmlStep.setAttribute("playerId", String.valueOf(s.getPlayerId()));
        xmlStep.setTextContent(s.getX()+""+s.getY());
        return xmlStep;
    }

    private Element createGameResult(){
        Element gameResult = document.createElement("GameResult");
        gameResult.setTextContent("Draw!");
        return gameResult;
    }

    private Element createGameResult(Element winner){
        Element gameResult = document.createElement("GameResult");
        gameResult.appendChild(winner);
        return gameResult;
    }

    @Override
    public void writeSaveGameFile(GamePlay gamePlay, String fileName) {

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
