package com.egorkhaziev.y_lab.vsPlayer.Save;

import com.egorkhaziev.y_lab.GameMenu;
import com.egorkhaziev.y_lab.vsPlayer.Save.JSON.JSONin;
import com.egorkhaziev.y_lab.vsPlayer.Save.XML.XMLin;
import org.xml.sax.SAXException;

import javax.xml.parsers.ParserConfigurationException;
import java.io.File;
import java.io.IOException;
import java.util.Locale;
import java.util.Scanner;

public class MenuViewXOGame {

    private GameMenu gameMenu;
    private String gameFile;
    private ViewSaveGame viewSaveGame;
    private XMLin xmlIn;
    private JSONin jsonIn;

    private Scanner sc = new Scanner(System.in);

    public MenuViewXOGame(GameMenu gameMenu) {
        this.gameMenu = gameMenu;
        this.xmlIn = new XMLin();
        this.jsonIn = new JSONin();
    }

    public void start() {
        System.out.print("\nPlease input your save game (with '.xml' or 'json', example 'game-0.json') : ");
        viewSaveGame = new ViewSaveGame();
        gameFile = sc.nextLine();

        File file = new File(gameFile);

        if (file.exists()) {
            fileCheck(gameFile);
        } else {
            System.out.println("Wrong save name.\n");
            gameMenu.changeGame();
        }

    }


    private void fileCheck(String gameFile) {

        try {
            if(gameFile.toLowerCase(Locale.ROOT).endsWith(".xml")){
                viewSaveGame.play(xmlIn.readFile(gameFile));
            }
            if(gameFile.toLowerCase(Locale.ROOT).endsWith(".json")){
                viewSaveGame.play(jsonIn.readFile(gameFile));
            }



        } catch (ParserConfigurationException e) {
            e.printStackTrace();
        } catch (IOException e) {
            e.printStackTrace();
        } catch (SAXException e) {
            e.printStackTrace();
        } catch (Exception e) {
            e.printStackTrace();
        }
    }

}
