package com.egorkhaziev.y_lab.vsPlayer.Save;

import com.egorkhaziev.y_lab.vsPlayer.Save.Model.GamePlay;

public interface WriteSaveGame {

    void writeSaveGameFile(GamePlay gamePlay, String fileName);

}
