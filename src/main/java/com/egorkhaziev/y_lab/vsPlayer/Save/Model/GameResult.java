package com.egorkhaziev.y_lab.vsPlayer.Save.Model;

import com.egorkhaziev.y_lab.vsPlayer.model.PlayerGame;
import lombok.Data;

@Data
public class GameResult {

    private PlayerGame winner;

    public GameResult() {
    }

}
