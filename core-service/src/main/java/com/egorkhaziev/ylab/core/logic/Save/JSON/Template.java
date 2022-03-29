package com.egorkhaziev.ylab.core.logic.Save.JSON;

import com.egorkhaziev.ylab.core.logic.model.GamePlay;
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
