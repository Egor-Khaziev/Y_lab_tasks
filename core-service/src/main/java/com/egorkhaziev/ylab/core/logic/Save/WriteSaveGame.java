package com.egorkhaziev.ylab.core.logic.Save;

import com.egorkhaziev.ylab.core.logic.model.GamePlay;

public interface WriteSaveGame {

    void writeSaveGameFile(GamePlay gamePlay, String fileName);

}
