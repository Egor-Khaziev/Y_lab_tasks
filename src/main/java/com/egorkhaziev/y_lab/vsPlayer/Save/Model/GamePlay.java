package com.egorkhaziev.y_lab.vsPlayer.Save.Model;

import com.egorkhaziev.y_lab.vsPlayer.model.Player;
import lombok.Data;

import java.util.ArrayList;
import java.util.List;

@Data
public class GamePlay {

    List<Player> players;

    Game game;

    GameResult GameResult;

    public GamePlay() {
        this.game = new Game();
        players = new ArrayList<>();
    }
}
