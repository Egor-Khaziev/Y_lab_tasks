package com.egorkhaziev.ylab.core.logic.Save.JSON;


import com.egorkhaziev.ylab.core.logic.Save.WriteSaveGame;
import com.egorkhaziev.ylab.core.logic.model.GamePlay;
import com.fasterxml.jackson.core.JsonProcessingException;
import com.fasterxml.jackson.databind.ObjectMapper;
import org.springframework.stereotype.Component;

import java.io.FileWriter;
import java.io.IOException;

public class JSONout implements WriteSaveGame {

    GamePlay gamePlay;
    String fileName;

    public JSONout(GamePlay gamePlay, int gameNumber) {
        this.gamePlay = gamePlay;
        this.fileName = "game-" + gameNumber + ".json";

        writeSaveGameFile(gamePlay, fileName);
    }

    @Override
    public void writeSaveGameFile(GamePlay gamePlay, String fileName) {

        ObjectMapper mapper = new ObjectMapper();
        String jsonStr = "";
        try {
            jsonStr = mapper
                    .writerWithDefaultPrettyPrinter()
                    .writeValueAsString(new Template(gamePlay));
        } catch (JsonProcessingException e) {
            e.printStackTrace();
        }


        try(FileWriter file = new FileWriter(fileName)) {
            file.write(jsonStr);
        } catch (IOException e) {
            e.printStackTrace();
        }

    }
}
