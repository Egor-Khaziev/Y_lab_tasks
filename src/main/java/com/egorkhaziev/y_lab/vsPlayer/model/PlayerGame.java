package com.egorkhaziev.y_lab.vsPlayer.model;

import com.egorkhaziev.y_lab.vsPlayer.Save.JSON.DTO.Player;
import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;
import lombok.extern.slf4j.Slf4j;

import java.io.Serializable;

@Data
@Slf4j
@AllArgsConstructor
@NoArgsConstructor
public class PlayerGame extends Player implements Serializable {

    private static final long serialVersionUID = 1L;

    private String name;
    private int win;
    private int loss;
    private int seriesWin;

    private int Id;

    // space for other feature


    public PlayerGame(String name) {
        this.name = name;

    }

    @Override
    public String toString() {
        return  name + " You win: " + win + " and loss: " + loss +
                "\nYour series of victories: " + seriesWin;
    }
}
