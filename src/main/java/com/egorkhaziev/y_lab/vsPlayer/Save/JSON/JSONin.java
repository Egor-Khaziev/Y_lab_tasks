package com.egorkhaziev.y_lab.vsPlayer.Save.JSON;

import com.egorkhaziev.y_lab.vsPlayer.Save.Model.GamePlay;
import com.egorkhaziev.y_lab.vsPlayer.Save.ReadSaveGame;

public class JSONin implements ReadSaveGame {

    GamePlay gamePlay;

    @Override
    public GamePlay readFile(String fileName) throws Exception {
        gamePlay = new GamePlay();

        return null;
    }
}
