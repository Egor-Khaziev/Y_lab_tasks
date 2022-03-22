package com.egorkhaziev.y_lab.vsPlayer.Save.JSON;

import com.egorkhaziev.y_lab.vsPlayer.Save.Model.GamePlay;
import lombok.Data;
import lombok.NoArgsConstructor;

@Data
@NoArgsConstructor
public class Template {
    private GamePlay gamePlay;

    public Template(GamePlay gamePlay) {
        this.gamePlay = gamePlay;
    }
}
