package com.egorkhaziev.y_lab.vsPlayer.XML;

import com.egorkhaziev.y_lab.menu.GameMenu;
import org.xml.sax.SAXException;

import javax.xml.parsers.ParserConfigurationException;
import java.io.File;
import java.io.IOException;
import java.util.Scanner;

public class XMLXOGame {

    private GameMenu gameMenu;
    private String xmlGame;

    private Scanner sc = new Scanner(System.in);

    public XMLXOGame(GameMenu gameMenu) {
        this.gameMenu = gameMenu;
    }

    public void start() {
        System.out.print("\nPlease input your save game (without '.xml', example 'game-0') : ");
        xmlGame = sc.nextLine()+".xml";

        File file = new File(xmlGame);

        if (file.exists()) {
            fileCheck(xmlGame);
        } else {
            System.out.println("Wrong save name.\n");
            gameMenu.changeGame();
        }

        XMLin.play();
    }


    private void fileCheck(String xmlGame) {

        try {
            XMLin.readXML(xmlGame);
        } catch (ParserConfigurationException e) {
            e.printStackTrace();
        } catch (IOException e) {
            e.printStackTrace();
        } catch (SAXException e) {
            e.printStackTrace();
        }
    }

}
