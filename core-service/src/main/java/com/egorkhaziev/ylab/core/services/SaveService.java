package com.egorkhaziev.ylab.core.services;

import com.egorkhaziev.ylab.core.logic.model.GamePlay;
import com.egorkhaziev.ylab.core.logic.ViewGameSave;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Service;

@RequiredArgsConstructor
@Service
public class SaveService {

    private final ViewGameSave viewGameSave;

    public void playSaveGame(GamePlay gamePlay){
        viewGameSave.play(gamePlay);
    }

}
