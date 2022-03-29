package com.egorkhaziev.ylab.core.controllers;

import com.egorkhaziev.ylab.core.logic.Save.JSON.Template;
import com.egorkhaziev.ylab.core.services.SaveService;
import lombok.RequiredArgsConstructor;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RestController;


@RestController
@RequiredArgsConstructor
public class SaveController {

    private final SaveService saveService;

    @PostMapping("/uploadJSON")
    public void uploadJSON(@RequestBody Template game) {
        saveService.playSaveGame(game.getGamePlay());
    }

}
