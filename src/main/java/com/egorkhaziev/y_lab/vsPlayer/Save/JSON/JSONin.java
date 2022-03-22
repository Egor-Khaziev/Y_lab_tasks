package com.egorkhaziev.y_lab.vsPlayer.Save.JSON;

import com.egorkhaziev.y_lab.vsPlayer.Save.Model.GamePlay;
import com.egorkhaziev.y_lab.vsPlayer.Save.ReadSaveGame;
import com.fasterxml.jackson.databind.ObjectMapper;

import java.io.*;

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
