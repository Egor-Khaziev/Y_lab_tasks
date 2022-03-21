package com.egorkhaziev.y_lab.vsPlayer.Save;

import com.egorkhaziev.y_lab.GameMenu;
import com.egorkhaziev.y_lab.vsPlayer.Save.JSON.JSONout;
import com.egorkhaziev.y_lab.vsPlayer.Save.Model.GamePlay;
import com.egorkhaziev.y_lab.vsPlayer.Save.XML.XMLout;

import java.util.Scanner;

public class MenuSaveXOGame {

    Scanner sc;
    GamePlay gamePlay;

    GameMenu gameMenu;

    public MenuSaveXOGame(GameMenu gameMenu, GamePlay gamePlay) {
        this.gameMenu = gameMenu;
        sc = gameMenu.sc;
        this.gamePlay = gamePlay;
        changeSave();
    }

    public void changeSave() {
        int saveNum;

        System.out.println(
                    "\n**********************************" +
                    "\nPlease change save mode: \n \n " +
                    "   1. json save. \n " +
                    "   2. xml save. \n\n " +
                    "   0. don't save the game ");

        System.out.print("\nInput your choice: ");

        if (!sc.hasNextInt()) {
            System.out.println("\nYour input is wrong. Please try again. \n");
            changeSave();
        }

        saveNum = sc.nextInt();

        switch (saveNum) {

            //игрок vs ИИ
            case (1):
                new JSONout(gamePlay, gameMenu.getGameNumber());
                break;

            //игрок vs игрок
            case (2):
                new XMLout(gamePlay, gameMenu.getGameNumber());
                break;

            //выход
            case (0):
                break;

            //Если введеное значение не соответствует вариантам
            default:
                System.out.println("\nYour input is wrong value. Please try again. \n");
                changeSave();
                break;
        }
    }

}
