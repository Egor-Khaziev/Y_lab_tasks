package com.egorkhaziev.ylab.core.logic.Save;

import com.egorkhaziev.ylab.core.logic.model.GamePlay;

public interface ReadSaveGame {
    public GamePlay readFile(String fileName)throws Exception;
}
