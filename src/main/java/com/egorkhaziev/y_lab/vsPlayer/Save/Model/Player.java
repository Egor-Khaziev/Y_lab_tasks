package com.egorkhaziev.y_lab.vsPlayer.Save.Model;

import com.fasterxml.jackson.annotation.JsonIgnore;
import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;
import lombok.extern.slf4j.Slf4j;

import java.io.Serializable;

@Data
@Slf4j
@AllArgsConstructor
@NoArgsConstructor
public class Player implements Serializable {

    private static final long serialVersionUID = 1L;

    private int Id;

    private String name;

    @JsonIgnore
    private int win;

    @JsonIgnore
    private int loss;

    @JsonIgnore
    private int seriesWin;

    private String simbol;



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
