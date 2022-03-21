package com.egorkhaziev.y_lab.vsPlayer;

import com.egorkhaziev.y_lab.GameMenu;
import com.egorkhaziev.y_lab.vsPlayer.Save.MenuSaveXOGame;
import com.egorkhaziev.y_lab.vsPlayer.Save.Model.*;
import com.egorkhaziev.y_lab.vsPlayer.model.PlayerGame;

import java.io.*;
import java.util.*;

public class XOGamePlayerVsPlayer {

    private final GameMenu gameMenu;

    private GamePlay gamePlay;

    private char[][] gameMap;
    public int x = 0;
    public int y = 0;
    public Scanner sc;

    private Map<String, PlayerGame> playerList = new HashMap<>();

    private PlayerGame playerGame1;
    private PlayerGame playerGame2;

    private boolean gameFinished;

    private final char EMPTY_DOT = '*';
    private final char X_DOT = 'X';
    private final char O_DOT = 'O';

    public static int gameCount;
                                            //    public static int gameNumber;
    public static int gameStep;

    public XOGamePlayerVsPlayer(GameMenu gameMenu) {
        this.gameMenu = gameMenu;

        this.sc = gameMenu.sc;
    }

    //old/new
    public void start() {
        gameStep=1;

        gamePlay = new GamePlay();

                                //        document = xmLout.createDocument();
                                //        gamePlay = document.createElement("gameplay");
                                //        document.appendChild(gamePlay);

        System.out.println("I glad to see you in classic XO game!\n" +
                "The winner is the one who takes 3 cells in a row\n" +
                "Good luck! The strongest will win!\n");

        //загрузка игроков из файла (строка)
        loadPlayers();
        //авторизация игроков
        authorization();
                                //        //XML новая партия игры
                                //        game = document.createElement("Game");
                                //        gamePlay.appendChild(game);
        //инициация поля
        initMap();
        //отрисовка поля
        paintMap();
        //new/old

        gameFinished = false;

        //собственно сам процесс
        while(true){
            if (gameLogic(playerGame1, X_DOT, playerGame2)) {break;}
            if (gameLogic(playerGame2, O_DOT, playerGame1)) {break;}
        }

        //обновление листа игроков
        baseRefresh(playerGame1, playerGame2);

        //сохранение игроков в файл строкой
        savePlayers();

        /****************** ПРОЦЕСС сохранения*/

        new MenuSaveXOGame(gameMenu, gamePlay);
        gameMenu.GameNumberPlusOne();

        //new
        againGameQuestion();
    }

    //new загрузка пользователей из файла
    private void loadPlayers() {
        playerList = new HashMap<>();
        //Проверка на наличие файла
        if(new File("players.txt").exists()) {
            try (ObjectInputStream objectInputStream = new ObjectInputStream(new FileInputStream("players.txt"))) {
                playerList = (HashMap) objectInputStream.readObject();
            } catch (ClassNotFoundException e) {
                e.printStackTrace();
            } catch (IOException e) {
                e.printStackTrace();
            }
        }
    }

    //new выгрузка пользователей в файл
    private void savePlayers() {

        try (ObjectOutputStream objectOutputStream = new ObjectOutputStream(new FileOutputStream("players.txt"))) {
            for (Map.Entry<String, PlayerGame> entry: playerList.entrySet()) {
                objectOutputStream.writeObject(playerList);
            }
        } catch (IOException ioEx){
            ioEx.getMessage();
        }
    }

    //new сохранение рейтинга
    private void saveToFile(PlayerGame winPlayerGame, PlayerGame lossPlayerGame) {
        try (BufferedWriter writter = new BufferedWriter(new FileWriter("rating.txt",true))) {
                writter.write(new Date() + " - Game finished \n");
                writter.write(winPlayerGame.getName() + " - Winner\n");
                writter.write(lossPlayerGame.getName() + " - Loss\n");
        }
        catch (IOException ioEx){
            ioEx.getMessage();
        }
    }

    private void baseRefresh(PlayerGame playerGame1, PlayerGame playerGame2) {
        playerList.put(playerGame1.getName(), playerGame1);
        playerList.put(playerGame2.getName(), playerGame2);
    }

    //new/old
    private boolean gameLogic(PlayerGame playerGame, char dot, PlayerGame enemy) {

            if (readyToStep()) {
                userStep(playerGame, dot);
                paintMap();
                if (isWin(dot)) {
                    System.out.println("FINISH");

                    gamePlay.getGameResult().setWinner(playerGame);
                                            //                    //XML победитель
                                            //                    Element winner = xmLout.createPlayer(document, playerGame.getId(), playerGame.getName(), String.valueOf(dot));
                                            //
                                            //                    Element gameResult = xmLout.createGameResult(document, winner);
                                            //                    gamePlay.appendChild(gameResult);

                    gameFinished =true;
                    //сохранение отчета в файл
                    saveToFile(playerGame, enemy);

                    playerGame.setSeriesWin(playerGame.getSeriesWin() + 1);
                    playerGame.setWin(playerGame.getWin() + 1);
                    System.out.println("WINNER:\n" + playerGame.toString());

                    enemy.setSeriesWin(0);
                    enemy.setLoss(enemy.getLoss() + 1);
                    System.out.println("\nLoser:\n" + enemy.toString());
                }
            }
        return gameFinished;
    }

    //new
    private void againGameQuestion() {
        sc.nextLine();
        System.out.print("\nAre you want playing again? yes/no ");

        String answer = sc.nextLine();
        if (answer.toLowerCase(Locale.ROOT).equals("yes")) {
            start();
        } else {
            gameMenu.changeGame();
        }
    }

    //new
    private void authorization() {

        playerGame1 = login("PlayerGame 1");
        playerGame2 = login("PlayerGame 2");
        playerGame1.setId(1);
        playerGame2.setId(2);

        gamePlay.getPlayerGames().add(playerGame1);
        gamePlay.getPlayerGames().add(playerGame2);

                                            //        //XML
                                            //        Element playerOne = xmLout.createPlayer(document, 1, playerGame1.getName(), "X");
                                            //        Element playerTwo = xmLout.createPlayer(document, 2, playerGame2.getName(), "O");
                                            //        gamePlay.appendChild(playerOne);
                                            //        gamePlay.appendChild(playerTwo);

        System.out.println("\nWelcome " + playerGame1.toString() + "\n***********  VS  ***********");
        System.out.println("Welcome " + playerGame2.toString());

    }

    //new
    private PlayerGame login(String player) {

        while (true) {
            System.out.print(player + " input your name, 3 symbol minimum: ");

            String name = sc.nextLine();

            //проверка базы
            if (playerList.containsKey(name)) {
                return playerList.get(name);
            }

            //создание нового пользователя
            //(пользователь будет добавлен в базу по окончании игры)
            if (name.length() >= 3) {
                return new PlayerGame(name);
            }
        }
    }

    //old/new
    private boolean isWin(char playerDot) {

        for (int i = 0; i < gameMap.length; i++) { // горизонталь
            for (int j = 0; j < gameMap[i].length - 2; j++) {
                if (gameMap[i][j] == playerDot && gameMap[i][j + 1] == playerDot && gameMap[i][j + 2] == playerDot) {
                    return true;
                }
            }
        }
        for (int i = 0; i < gameMap.length - 2; i++) { //вертикаль
            for (int j = 0; j < gameMap[i].length; j++) {
                if (gameMap[i][j] == playerDot && gameMap[i + 1][j] == playerDot && gameMap[i + 2][j] == playerDot) {
                    return true;
                }
            }
        }
        for (int i = 0; i < gameMap.length - 2; i++) { //диагональ левый верхний - правый нижний
            for (int j = 0; j < gameMap[i].length - 2; j++) {
                if (gameMap[i][j] == playerDot && gameMap[i + 1][j + 1] == playerDot && gameMap[i + 2][j + 2] == playerDot) {
                    return true;
                }
            }
        }
        for (int i = 2; i < gameMap.length; i++) { //диагональ левый нижний - правый верхний
            for (int j = 0; j < gameMap[i].length - 2; j++) {
                if (gameMap[i][j] == playerDot && gameMap[i - 1][j + 1] == playerDot && gameMap[i - 2][j + 2] == playerDot) {
                    return true;
                }
            }
        }
        return false;////////////// нет совпадений
    }

    //old
    private boolean readyToStep() {
        if (gameCount == 0) {
            System.out.println("FRIENDLY WIN");

                                        //            Element gameResult = xmLout.createGameResult(document);
                                        //            gamePlay.appendChild(gameResult);
            gameFinished = true;
            return false;
        }
        return true;
    }

    //old
    private void userStep(PlayerGame playerGame, char dot) {

        System.out.println(playerGame.getName() + " input your step (expire: \"2 2\")");
        giveMe2Int();
        if (gameMap.length >= x && gameMap[0].length >= y && (gameMap[x - 1][y - 1] != 'X' && gameMap[x - 1][y - 1] != 'O')) {
            gameMap[x - 1][y - 1] = dot;
            gameCount--;

            Step step = new Step();
            step.setPlayerId(playerGame.getId());
            step.setX(x);
            step.setY(y);
            step.setNum(gameStep);

            gamePlay.getGame().getSteps().add(step);
                                                        //            //XML ход
                                                        //            Element step = xmLout.createStep(document, gameStep, gameCount%2==0?1:2, x, y);
                                                        //            game.appendChild(step);
            gameStep++;

        } else {
            paintMap();
            System.out.println("It is not correct or free");
            userStep(playerGame, dot);
        }
    }


    //new/old
    private void initMap() {

        gameCount = 0;
        gameMap = new char[3][3];

        for (char[] line : gameMap) {
            for (int i= 0;i<line.length;i++ ) {
                line[i] = EMPTY_DOT;
                gameCount++;
            }
        }
    }
    //new/old
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
    //old
    private void giveMe2Int() {
        boolean num1 = false;
        boolean num2 = false;
        int n1 = 0;
        int n2 = 0;

        if (sc.hasNextInt()) {
            n1 = sc.nextInt();
            num1 = true;
        }
        if (sc.hasNextInt()) {
            n2 = sc.nextInt();
            num2 = true;
        }
        if (num1 && num2) {
            y = n1;
            x = n2;
            sc.nextLine();
        } else {
            System.out.println("Your input not a number.\nPlease, try again.");
            sc.nextLine();
            giveMe2Int();
        }
    }


}
