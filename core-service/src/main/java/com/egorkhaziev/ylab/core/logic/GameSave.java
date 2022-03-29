package com.egorkhaziev.ylab.core.logic;

import com.egorkhaziev.ylab.core.logic.model.Player;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Component;

import java.io.*;
import java.util.Date;
import java.util.Map;

@Component
@RequiredArgsConstructor
public class GameSave {

    private final GamePlayersStorage players;

    public void savePlayers() {

        try (ObjectOutputStream objectOutputStream = new ObjectOutputStream(new FileOutputStream("players.txt"))) {
            for (Map.Entry<String, Player> entry: players.getPlayerList().entrySet()) {
                objectOutputStream.writeObject(players.getPlayerList());
            }
        } catch (IOException ioEx){
            ioEx.getMessage();
        }
    }

    //new сохранение рейтинга
    public void saveToFile(Player winPlayer, Player lossPlayer) {
        try (BufferedWriter writter = new BufferedWriter(new FileWriter("rating.txt",true))) {
            writter.write(new Date() + " - Game finished \n");
            writter.write(winPlayer.getName() + " - Winner\n");
            writter.write(lossPlayer.getName() + " - Loss\n");
        }
        catch (IOException ioEx){
            ioEx.getMessage();
        }
    }


}
