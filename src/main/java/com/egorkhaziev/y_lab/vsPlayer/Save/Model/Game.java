package com.egorkhaziev.y_lab.vsPlayer.Save.Model;

import lombok.Data;

import java.util.ArrayList;
import java.util.List;

@Data
public class Game {

    private List<Step> steps;

    public Game() {
        this.steps = new ArrayList<>();
    }
}
