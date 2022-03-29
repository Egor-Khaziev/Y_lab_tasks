package com.egorkhaziev.ylab.core.controllers;

import com.egorkhaziev.ylab.core.utils.XORequest;
import com.egorkhaziev.ylab.core.utils.XOResponse;
import com.egorkhaziev.ylab.core.services.GameService;
import lombok.RequiredArgsConstructor;
import lombok.extern.slf4j.Slf4j;
import org.springframework.web.bind.annotation.*;

@RestController
@RequiredArgsConstructor
@RequestMapping("/gameplay")
@Slf4j
public class GameController {

    private final GameService gameService;

    @GetMapping
    public XOResponse startNewGame(@RequestParam String player1, @RequestParam String player2) {
        log.warn("new game");
        return gameService.startNewGame(player1, player2);
    }

    @PostMapping("/step")
    public XOResponse step(@RequestBody XORequest request) {

        log.warn("step");
        return gameService.step(request);
    }

}
