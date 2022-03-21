package com.egorkhaziev.y_lab.vsPlayer.Save;

import com.egorkhaziev.y_lab.GameMenu;
import com.egorkhaziev.y_lab.vsPlayer.Save.Model.GamePlay;
import com.egorkhaziev.y_lab.vsPlayer.Save.Model.Step;
import com.egorkhaziev.y_lab.vsPlayer.model.PlayerGame;

import java.util.List;

import static java.lang.Thread.sleep;

public class ViewSaveGame {


    private final char EMPTY_DOT = '*';
    private static char X_DOT = 'X';
    private static char O_DOT = 'O';

    private char[][] gameMap;

    public void play(GamePlay gamePlay) {

        List<Step> steps = gamePlay.getGame().getSteps();
        String winner;
        List<PlayerGame> playerGames = gamePlay.getPlayerGames();

        if(gamePlay.getPlayerGames().size()==3){
            winner = gamePlay.getPlayerGames().get(2).getName();
        } else {
            winner = null;
        }



        System.out.println(playerGames.get(0).getName() + " VS " + playerGames.get(1).getName());
        initMap();
        paintMap();

        for (int i = 0; i <= steps.size()-1; i++) {

            int x = gamePlay.getGame().getSteps().get(i).getX();
            int y = gamePlay.getGame().getSteps().get(i).getY();
            gameMap[x - 1][y - 1] = ((i % 2 == 1) ? X_DOT : O_DOT);
            paintMap();
            System.out.println("step "+((i % 2 == 1) ? playerGames.get(1).getName() : playerGames.get(0).getName()));
            sleeping(1000);
        }

        if (winner!=null) {
            System.out.println("*** WINNER: " + winner + " ***");
        } else {
            System.out.println("*** DRAW! ***");
        }
        sleeping(2500);

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
