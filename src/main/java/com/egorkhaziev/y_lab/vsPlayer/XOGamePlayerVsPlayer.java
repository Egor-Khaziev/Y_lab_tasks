package com.egorkhaziev.y_lab.vsPlayer;

import com.egorkhaziev.y_lab.menu.GameMenu;
import com.egorkhaziev.y_lab.vsPlayer.XML.XMLout;
import com.egorkhaziev.y_lab.vsPlayer.model.Player;
import org.w3c.dom.Document;
import org.w3c.dom.Element;

import java.io.*;
import java.util.*;

public class XOGamePlayerVsPlayer {

    private final GameMenu gameMenu;
    private final XMLout xmLout;

    private Element gamePlay;
    private Document document;
    private Element game;

    private char[][] gameMap;
    public int x = 0;
    public int y = 0;
    public Scanner sc;

    private Map<String, Player> playerList = new HashMap<>();

    private Player player1;
    private Player player2;

    private boolean gameFinished;

    private final char EMPTY_DOT = '*';
    private final char X_DOT = 'X';
    private final char O_DOT = 'O';

    public static int gameCount;
    public static int gameNumber;
    public static int gameStep;

    public XOGamePlayerVsPlayer(GameMenu gameMenu) {
        this.gameMenu = gameMenu;
        this.xmLout = new XMLout();

        this.sc = new Scanner(System.in);
    }

    //old/new
    public void start() {
        gameStep=1;

        document = XMLout.createDocument();
        gamePlay = document.createElement("gameplay");
        document.appendChild(gamePlay);

        System.out.println("I glad to see you in classic XO game!\n" +
                "The winner is the one who takes 3 cells in a row\n" +
                "Good luck! The strongest will win!\n");

        //загрузка игроков из файла (строка)
        loadPlayers();
        //авторизация игроков
        authorization();
        //XML новая партия игры
        game = document.createElement("Game");
        gamePlay.appendChild(game);
        //инициация поля
        initMap();
        //отрисовка поля
        paintMap();
        //new/old

        gameFinished = false;

        //собственно сам процесс
        while(true){
            if (gameLogic(player1, X_DOT, player2)) {break;}
            if (gameLogic(player2, O_DOT, player1)) {break;}
        }

        //обновление листа игроков
        baseRefresh(player1, player2);

        //сохранение игроков в файл строкой
        savePlayers();
        //XML сохранение
        String fileName = "game-" + gameNumber + ".xml";
        XMLout.saveAsFile(document, fileName);
        gameNumber++;
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
            for (Map.Entry<String,Player> entry: playerList.entrySet()) {
                objectOutputStream.writeObject(playerList);
            }
        } catch (IOException ioEx){
            ioEx.getMessage();
        }
    }

    //new сохранение рейтинга
    private void saveToFile(Player winPlayer, Player lossPlayer) {
        try (BufferedWriter writter = new BufferedWriter(new FileWriter("rating.txt",true))) {
                writter.write(new Date() + " - Game finished \n");
                writter.write(winPlayer.getName() + " - Winner\n");
                writter.write(lossPlayer.getName() + " - Loss\n");
        }
        catch (IOException ioEx){
            ioEx.getMessage();
        }
    }

    private void baseRefresh(Player player1, Player player2) {
        playerList.put(player1.getName(), player1);
        playerList.put(player2.getName(), player2);
    }

    //new/old
    private boolean gameLogic(Player player,char dot, Player enemy) {

            if (readyToStep()) {
                userStep(player, dot);
                paintMap();
                if (isWin(dot)) {
                    System.out.println("FINISH");


                    //XML победитель
                    Element winner = XMLout.createXMLPlayer(document, player.getId(), player.getName(), String.valueOf(dot));

                    Element gameResult = XMLout.createGameResult(document, winner);
                    gamePlay.appendChild(gameResult);

                    gameFinished =true;
                    //сохранение отчета в файл
                    saveToFile(player, enemy);

                    player.setSeriesWin(player.getSeriesWin() + 1);
                    player.setWin(player.getWin() + 1);
                    System.out.println("WINNER:\n" + player.toString());

                    enemy.setSeriesWin(0);
                    enemy.setLoss(enemy.getLoss() + 1);
                    System.out.println("\nLoser:\n" + enemy.toString());
                }
            }
        return gameFinished;
    }

    //new
    private void againGameQuestion() {
        System.out.print("Are you want playing again? yes/no ");
        if (sc.nextLine().toLowerCase(Locale.ROOT).equals("yes")) {
            start();
        } else {
            gameMenu.changeGame();
        }
    }

    //new
    private void authorization() {

        player1 = login("Player 1");
        player2 = login("Player 2");

        //XML
        Element playerOne = XMLout.createXMLPlayer(document, 1, player1.getName(), "X");
        Element playerTwo = XMLout.createXMLPlayer(document, 2, player2.getName(), "O");
        gamePlay.appendChild(playerOne);
        gamePlay.appendChild(playerTwo);

        System.out.println("\nWelcome " + player1.toString() + "\n***********  VS  ***********");
        System.out.println("Welcome " + player2.toString());

    }

    //new
    private Player login(String player) {

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
                return new Player(name);
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

            Element gameResult = XMLout.createGameResult(document);
            gamePlay.appendChild(gameResult);
            gameFinished = true;
            return false;
        }
        return true;
    }

    //old
    private void userStep(Player player, char dot) {

        System.out.println(player.getName() + " input your step (expire: \"2 2\")");
        giveMe2Int();
        if (gameMap.length >= x && gameMap[0].length >= y && (gameMap[x - 1][y - 1] != 'X' && gameMap[x - 1][y - 1] != 'O')) {
            gameMap[x - 1][y - 1] = dot;
            gameCount--;
            //XML ход
            Element step = XMLout.createXMLStep(document, gameStep, gameCount%2==0?1:2, x, y);
            game.appendChild(step);
            gameStep++;

        } else {
            paintMap();
            System.out.println("It is not correct or free");
            userStep(player, dot);
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
