package com.egorkhaziev.y_lab.vsPlayer.Save;

import com.egorkhaziev.y_lab.GameMenu;
import com.egorkhaziev.y_lab.vsPlayer.Save.Model.Step;
import com.egorkhaziev.y_lab.vsPlayer.model.Player;

import java.util.ArrayList;

import static java.lang.Thread.sleep;

public class ViewSaveGame {

    private ArrayList<Step> steps;
    private String winner;
    private ArrayList<Player> players;

    private final char EMPTY_DOT = '*';
    private static char X_DOT = 'X';
    private static char O_DOT = 'O';

    private char[][] gameMap;

    public void play() {


        if(players.size()==3){
            winner = players.get(2).getName();
        }



        System.out.println(players.get(0).getName() + " VS " + players.get(1).getName());
        initMap();
        paintMap();

        for (int i = 0; i <= steps.size()-1; i++) {

            int x = steps.get(i).getX();
            int y = steps.get(i).getY();
            gameMap[x - 1][y - 1] = ((i % 2 == 1) ? X_DOT : O_DOT);
            paintMap();
            System.out.println("step "+((i % 2 == 1) ? players.get(1).getName() : players.get(0).getName()));
            sleeping(1000);
        }

        System.out.println("WINNER: " + winner);
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
