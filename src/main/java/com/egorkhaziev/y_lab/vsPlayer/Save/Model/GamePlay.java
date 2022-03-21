package com.egorkhaziev.y_lab.vsPlayer.Save.Model;

import com.egorkhaziev.y_lab.vsPlayer.model.PlayerGame;
import lombok.Data;

import java.util.ArrayList;
import java.util.List;

@Data
public class GamePlay {

    private List<PlayerGame> playerGames;

    private Game game;

    private GameResult gameResult;

    public GamePlay() {
        this.game = new Game();
        playerGames = new ArrayList<>();
        gameResult = new GameResult();
    }


}
