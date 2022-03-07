package com.egorkhaziev.y_lab.menu;

import com.egorkhaziev.y_lab.vsPC.XOGamePlayerVsPC;
import com.egorkhaziev.y_lab.vsPlayer.XOGamePlayerVsPlayer;

import java.util.Scanner;

public class GameMenu {

    private final XOGamePlayerVsPlayer xoGamePlayerVsPlayer;
    private final XOGamePlayerVsPC xoGamePlayerVsPC;

    public Scanner sc = new Scanner(System.in);

    public GameMenu() {
        xoGamePlayerVsPlayer = new XOGamePlayerVsPlayer(this);
        xoGamePlayerVsPC = new XOGamePlayerVsPC(this);
    }

    //выбор игры:
    // 1. Вариант игры против ИИ написанный год назад
    // 2. Текущая задача. Игрок против игрока.
    public void changeGame(){
        int gameNum;

        System.out.println(
                "Please change your game: \n \n " +
                "   1. XO game (modified) - Player vs PC (my experiment from 28.01.2021) \n " +
                "   2. XO game - Player vs Player\n " +
                "   3. Quit");

        System.out.print("\nInput your choice: ");

        if (!sc.hasNextInt()) {
            System.out.println("\nYour input is wrong. Please try again. \n");
            changeGame();}

        gameNum = sc.nextInt();

        switch (gameNum) {

                //игрок vs ИИ
            case  (1):
                xoGamePlayerVsPC.start();
                break;

                //игрок vs игрок
            case (2):
                xoGamePlayerVsPlayer.start();
                break;

                //выход
            case (3):
                break;

            //Если введеное значение не соответствует вариантам
            default:
                System.out.println("\nYour input is wrong. Please try again. \n");
                changeGame();
                break;
        }
    }
}
