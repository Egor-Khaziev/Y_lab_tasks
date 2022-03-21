package com.egorkhaziev.y_lab;

import com.egorkhaziev.y_lab.vsPC.XOGamePlayerVsPC;
import com.egorkhaziev.y_lab.vsPlayer.Save.MenuViewXOGame;
import com.egorkhaziev.y_lab.vsPlayer.XOGamePlayerVsPlayer;

import java.util.Scanner;

public class GameMenu {

    public Scanner sc = new Scanner(System.in);

    private int gameNumber;


    public int getGameNumber() {
        return gameNumber;
    }

    public void GameNumberPlusOne() {
        gameNumber++;
    }



    public GameMenu() {
        gameNumber=1;
    }

    //выбор игры:
    // 1. Вариант игры против ИИ написанный год назад
    // 2. Текущая задача. Игрок против игрока.
    public void changeGame(){
        int gameNum;

        System.out.println(
                "\n\n***********************************************************************\n" +
                "\nPlease change your game: \n \n " +
                "   1. XO game (modified) - PlayerGame vs PC (my experiment from 28.01.2021) \n " +
                "   2. XO game - PlayerGame vs PlayerGame\n " +
                "   3. Quit\n\n" +
                "   0. Repeat game ");

        System.out.print("\nInput your choice: ");

        if (!sc.hasNextInt()) {
            System.out.println("\nYour input is wrong. Please try again. \n");
            changeGame();}

        gameNum = sc.nextInt();

        switch (gameNum) {

                //игрок vs ИИ
            case  (1):
                new XOGamePlayerVsPC(this).start();
                break;

                //игрок vs игрок
            case (2):
                new XOGamePlayerVsPlayer(this).start();
                break;

                //выход
            case (3):
                sc.close();
                break;

            //XML Повтор игры
            case (0):
                new MenuViewXOGame(this).start();
                break;

            //Если введеное значение не соответствует вариантам
            default:
                System.out.println("\nYour input is wrong. Please try again. \n");
                changeGame();
                break;
        }
    }
}
