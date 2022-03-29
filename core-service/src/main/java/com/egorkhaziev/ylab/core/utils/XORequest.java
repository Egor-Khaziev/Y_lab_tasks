package com.egorkhaziev.ylab.core.utils;

import com.egorkhaziev.ylab.core.DTO.PlayerDTO;
import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

@Data
@AllArgsConstructor
@NoArgsConstructor
public class XORequest {

    int x;
    int y;

    PlayerDTO playerDTO;

}
