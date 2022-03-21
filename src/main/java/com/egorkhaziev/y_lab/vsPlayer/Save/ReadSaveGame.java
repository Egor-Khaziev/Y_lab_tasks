package com.egorkhaziev.y_lab.vsPlayer.Save;


import com.egorkhaziev.y_lab.vsPlayer.Save.Model.GamePlay;

public interface ReadSaveGame {
    public GamePlay readFile(String fileName)throws Exception;
}
