package com.egorkhaziev.ylab.core.logic;

import lombok.Data;
import lombok.extern.slf4j.Slf4j;
import org.springframework.stereotype.Component;

@Data
@Slf4j
public class GameMap {

    private char[][] gameMap;

    private int gameCountDown;

    private final char EMPTY_DOT = '*';
    private final char X_DOT = 'X';
    private final char O_DOT = 'O';

    public void initMap() {
        gameCountDown =0;

        gameMap = new char[3][3];

        for (char[] line : gameMap) {
            for (int i= 0;i<line.length;i++ ) {
                line[i] = EMPTY_DOT;
                gameCountDown++;
            }
        }
        log.debug("init map +");
    }

    public void gameCountDownMinus() {
        gameCountDown--;
    }

    public void paintMap() {
        for (int i = 0; i < gameMap.length; i++) {
            System.out.println();
            System.out.print("|");
            for (int j = 0; j < gameMap[i].length; j++) {
                System.out.print(gameMap[i][j] + "|");
            }
        }
        System.out.println();
        log.debug("paint map +");
    }
}
