package com.egorkhaziev.ylab.core.logic.model;

import lombok.Data;
import lombok.extern.slf4j.Slf4j;
import org.springframework.stereotype.Component;

import java.util.ArrayList;
import java.util.List;

@Data
@Slf4j
@Component
public class GamePlay {

    private List<Player> player;

    private Game game;

    private GameResult gameResult;

    public GamePlay() {
        this.game = new Game();
        player = new ArrayList<>();
        gameResult = new GameResult();
        log.debug("gamePlay +");
    }


}
