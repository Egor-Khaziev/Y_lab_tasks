package com.egorkhaziev.y_lab.vsPlayer.Save.Model;

import java.util.ArrayList;
import java.util.List;

public class Game {

    private List<Step> steps;

    public List<Step> getSteps() {
        return steps;
    }

    public Game() {
        this.steps = new ArrayList<>();
    }
}
