package com.egorkhaziev.ylab.core.utils;

import com.egorkhaziev.ylab.core.DTO.PlayerDTO;
import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;
import org.springframework.stereotype.Component;

@Component
@Data
@NoArgsConstructor
@AllArgsConstructor
public class XOResponse {


    PlayerDTO player1;
    PlayerDTO player2;

    private char[][] gameMap;

}
