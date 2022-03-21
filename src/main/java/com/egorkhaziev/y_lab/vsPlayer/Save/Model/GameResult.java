package com.egorkhaziev.y_lab.vsPlayer.Save.Model;

import com.egorkhaziev.y_lab.vsPlayer.model.Player;

public class GameResult {

    private Player player;

    public GameResult(Player player) {
        this.player = player;
    }

    public Player getPlayer() {
        return player;
    }
}
