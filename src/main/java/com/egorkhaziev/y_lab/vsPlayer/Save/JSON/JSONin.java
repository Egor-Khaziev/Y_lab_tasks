package com.egorkhaziev.y_lab.vsPlayer.Save.JSON;

import com.egorkhaziev.y_lab.vsPlayer.Save.Model.GamePlay;
import com.egorkhaziev.y_lab.vsPlayer.Save.Model.Step;
import com.egorkhaziev.y_lab.vsPlayer.Save.ReadSaveGame;
import com.egorkhaziev.y_lab.vsPlayer.model.Player;

import java.util.List;

public class JSONin implements ReadSaveGame {

    List<Step> steps;
    List<Player> players;

    GamePlay gamePlay;

    @Override
    public GamePlay readFile(String fileName) throws Exception {
        gamePlay = new GamePlay();

        return null;
    }
}
