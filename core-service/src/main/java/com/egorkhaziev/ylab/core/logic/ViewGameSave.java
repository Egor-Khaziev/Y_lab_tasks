package com.egorkhaziev.ylab.core.logic;

import com.egorkhaziev.ylab.core.logic.model.GamePlay;
import com.egorkhaziev.ylab.core.logic.model.Player;
import com.egorkhaziev.ylab.core.logic.model.Step;
import org.springframework.stereotype.Component;

import java.util.List;

import static java.lang.Thread.sleep;

@Component
public class ViewGameSave {


    private final char EMPTY_DOT = '*';
    private static char X_DOT = 'X';
    private static char O_DOT = 'O';

    private char[][] gameMap;

    public void play(GamePlay gamePlay) {

        List<Step> steps = gamePlay.getGame().getStep();
        String winner = null;
        List<Player> players = gamePlay.getPlayer();

        if (gamePlay.getGameResult().getPlayer()!=null) {
            winner = gamePlay.getGameResult().getPlayer().getName();
        }



        System.out.println(players.get(0).getName() + " VS " + players.get(1).getName());
        initMap();
        paintMap();

        for (int i = 0; i <= steps.size()-1; i++) {

            int x = gamePlay.getGame().getStep().get(i).getX();
            int y = gamePlay.getGame().getStep().get(i).getY();
            gameMap[x - 1][y - 1] = ((i % 2 == 1) ? X_DOT : O_DOT);
            paintMap();
            System.out.println("step "+((i % 2 == 1) ? players.get(1).getName() : players.get(0).getName()));
            sleeping(1000);
        }

        if (winner!=null) {
            System.out.println("*** WINNER: " + winner + " ***");
        } else {
            System.out.println("*** DRAW! ***");
        }
        sleeping(2500);
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
