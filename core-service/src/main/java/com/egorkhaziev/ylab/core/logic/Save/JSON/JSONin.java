package com.egorkhaziev.ylab.core.logic.Save.JSON;


import com.egorkhaziev.ylab.core.logic.Save.ReadSaveGame;
import com.egorkhaziev.ylab.core.logic.model.GamePlay;
import com.fasterxml.jackson.databind.ObjectMapper;

import java.io.BufferedReader;
import java.io.FileNotFoundException;
import java.io.FileReader;
import java.io.IOException;

public class JSONin implements ReadSaveGame {


    @Override
    public GamePlay readFile(String fileName) throws Exception {

        String jsonStr = fileToString(fileName);
        ObjectMapper mapper = new ObjectMapper();
        Template jsonTemp = mapper.readValue(jsonStr, Template.class);
        return jsonTemp.getGamePlay();
    }

    public static String fileToString(String fileName) {
        StringBuilder sb = new StringBuilder();
        try (FileReader fr = new FileReader(fileName)) {
            BufferedReader br = new BufferedReader(fr);
            String str;
            while ((str = br.readLine()) != null) {
                sb.append(str.replaceAll(" ", ""));
            }
        } catch (FileNotFoundException e) {
            e.printStackTrace();
        } catch (IOException e) {
            e.printStackTrace();
        }
        return sb.toString();
    }
}
