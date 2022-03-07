package com.egorkhaziev.y_lab.vsPlayer.model;

import lombok.AllArgsConstructor;
import lombok.Data;

import java.io.Serializable;

@Data
@AllArgsConstructor
public class Player implements Serializable {

    private static final long serialVersionUID = 1L;

    private String name;
    private int win;
    private int loss;
    private int seriesWin;

    // space for other feature


    public Player(String name) {
        this.name = name;
    }

    @Override
    public String toString() {
        return  name + " You win: " + win + " and loss: " + loss +
                "\nYour series of victories: " + seriesWin;
    }
}
