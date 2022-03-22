package com.egorkhaziev.y_lab.vsPlayer.Save.Model;

import lombok.Data;

import java.util.ArrayList;
import java.util.List;

@Data
public class GamePlay {

    private List<Player> player;

    private Game game;

    private GameResult gameResult;

    public GamePlay() {
        this.game = new Game();
        player = new ArrayList<>();
        gameResult = new GameResult();
    }


}
