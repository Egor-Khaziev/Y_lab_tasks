package com.egorkhaziev.ylab.core.logic;

import com.egorkhaziev.ylab.core.logic.Save.JSON.JSONout;
import com.egorkhaziev.ylab.core.logic.Save.XML.XMLout;
import com.egorkhaziev.ylab.core.logic.model.GamePlay;
import com.egorkhaziev.ylab.core.logic.model.Player;
import com.egorkhaziev.ylab.core.logic.model.Step;
import com.egorkhaziev.ylab.core.exceptions.WrongInputException;
import com.egorkhaziev.ylab.core.utils.XOResponse;
import lombok.Data;
import lombok.extern.slf4j.Slf4j;
import org.springframework.stereotype.Component;

import javax.annotation.PostConstruct;

@Component
@Slf4j
@Data
public class GameProcess {

    private GamePlay gamePlay;
    private GameMap gameMap;
    private final GamePlayersStorage players;
    private final GameSave gameSave;
//    private final XMLout xmLout;
//    private final JSONout jsoNout;

    private int x = 0;
    private int y = 0;

    private boolean gameFinished;

    private final char EMPTY_DOT = '*';
    private final char X_DOT = 'X';
    private final char O_DOT = 'O';

    public static int gameNumber;
    public static int gameStep;

    @PostConstruct
    private void init(){
        gameNumber=100;
    }

    public void startNewGame(String player1Name, String player2Name) {
        log.debug("game creating start");
        gameStep = 0;
        gameMap = new GameMap();
        gamePlay = new GamePlay();
        gameFinished = false;

        players.loadPlayers();
        players.authorization(player1Name, player2Name);
        players.getPlayer1().setSimbol((gameMap.getX_DOT()));
        players.getPlayer2().setSimbol((gameMap.getO_DOT()));
        gamePlay.getPlayer().add(players.getPlayer1());
        gamePlay.getPlayer().add(players.getPlayer2());
        gameMap.initMap();
        gameMap.paintMap();
    }

    public char[][] step (int x, int y, Player player){

        if(gameFinished){
            System.out.println("GAME IS OVER\nPLEASE START NEW GAME");
            return gameMap.getGameMap();
        }

        if (gameMap.getGameMap().length >= x
                && gameMap.getGameMap()[0].length >= y
                && (gameMap.getGameMap()[x - 1][y - 1] != 'X' && gameMap.getGameMap()[x - 1][y - 1] != 'O')) {
            gameMap.getGameMap()[x - 1][y - 1] = player.getSimbol();
            gameMap.gameCountDownMinus();

            Step step = new Step();
            step.setPlayerId(player.getId());
            step.setX(x);
            step.setY(y);
            step.setNum(gameStep);

            gamePlay.getGame().getStep().add(step);
            gameStep++;

            log.debug("step="+ gameStep + " x="+x+" y="+y);

        } else {
            log.debug("wrong input :(");
            throw new WrongInputException("неверные значения ввода");
        }

        gameMap.paintMap();
        if(winCheck(player)){return gameMap.getGameMap();}
        if(friendlyWinCheck()){ gameFinished = true;}

        return gameMap.getGameMap();
    }

    private boolean friendlyWinCheck() {
        if (gameMap.getGameCountDown() == 0) {
            System.out.println("GAME IS OVER\nFRIENDLY WIN");
            log.debug("registered friendly win");
            saveProcedure();
            return true;
        }
        return false;
    }

    private boolean winCheck(Player player) {

        if (isWin(player.getSimbol())) {
            System.out.println("GAME IS OVER");

            gamePlay.getGameResult().setPlayer(player);

            gameFinished =true;
            //сохранение отчета в файл
            Player enemy = player.getId()==players.getPlayer1().getId()?players.getPlayer2():players.getPlayer1();
            gameSave.saveToFile(player, enemy);

            player.setSeriesWin(player.getSeriesWin() + 1);
            player.setWin(player.getWin() + 1);
            System.out.println("WINNER:\n" + player.toString());

            enemy.setSeriesWin(0);
            enemy.setLoss(enemy.getLoss() + 1);
            System.out.println("\nLoser:\n" + enemy.toString());
            saveProcedure();
        }
        return gameFinished;
    }

    private boolean isWin(char playerDot) {

        for (int i = 0; i < gameMap.getGameMap().length; i++) { // горизонталь
            for (int j = 0; j < gameMap.getGameMap()[i].length - 2; j++) {
                if (gameMap.getGameMap()[i][j] == playerDot && gameMap.getGameMap()[i][j + 1] == playerDot && gameMap.getGameMap()[i][j + 2] == playerDot) {
                    return true;
                }
            }
        }
        for (int i = 0; i < gameMap.getGameMap().length - 2; i++) { //вертикаль
            for (int j = 0; j < gameMap.getGameMap()[i].length; j++) {
                if (gameMap.getGameMap()[i][j] == playerDot && gameMap.getGameMap()[i + 1][j] == playerDot && gameMap.getGameMap()[i + 2][j] == playerDot) {
                    return true;
                }
            }
        }
        for (int i = 0; i < gameMap.getGameMap().length - 2; i++) { //диагональ левый верхний - правый нижний
            for (int j = 0; j < gameMap.getGameMap()[i].length - 2; j++) {
                if (gameMap.getGameMap()[i][j] == playerDot && gameMap.getGameMap()[i + 1][j + 1] == playerDot && gameMap.getGameMap()[i + 2][j + 2] == playerDot) {
                    return true;
                }
            }
        }
        for (int i = 2; i < gameMap.getGameMap().length; i++) { //диагональ левый нижний - правый верхний
            for (int j = 0; j < gameMap.getGameMap()[i].length - 2; j++) {
                if (gameMap.getGameMap()[i][j] == playerDot && gameMap.getGameMap()[i - 1][j + 1] == playerDot && gameMap.getGameMap()[i - 2][j + 2] == playerDot) {
                    return true;
                }
            }
        }
        return false;////////////// нет совпадений
    }

    private void saveProcedure(){
        gameSave.savePlayers();
        players.baseRefresh();
        new XMLout(gamePlay,gameNumber);
        new JSONout(gamePlay,gameNumber);
    }

}
