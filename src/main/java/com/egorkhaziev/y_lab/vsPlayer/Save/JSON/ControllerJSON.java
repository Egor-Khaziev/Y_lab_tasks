package com.egorkhaziev.y_lab.vsPlayer.Save.JSON;

import com.egorkhaziev.y_lab.vsPlayer.Save.Model.GamePlay;

public class ControllerJSON {

    GamePlay gamePlay;
    int gameNumber;

    public ControllerJSON(GamePlay gamePlay, int gameNumber) {
        this.gamePlay = gamePlay;
        this.gameNumber = gameNumber;
    }
}
