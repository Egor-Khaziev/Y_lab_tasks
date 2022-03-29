package com.egorkhaziev.ylab.core.logic.model;

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

    private int Id;

    private String name;

    @JsonIgnore
    private int win;

    @JsonIgnore
    private int loss;

    @JsonIgnore
    private int seriesWin;

    private char simbol;

    public Player(String name) {
        this.name = name;

    }

    @Override
    public String toString() {
        return  name + " You win: " + win + " and loss: " + loss +
                "\nYour series of victories: " + seriesWin;
    }
}
