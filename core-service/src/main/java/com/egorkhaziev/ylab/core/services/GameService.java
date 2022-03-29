package com.egorkhaziev.ylab.core.services;

import com.egorkhaziev.ylab.core.logic.GamePlayersStorage;
import com.egorkhaziev.ylab.core.utils.XORequest;
import com.egorkhaziev.ylab.core.utils.XOResponse;
import com.egorkhaziev.ylab.core.converters.ConverterPlayer;
import com.egorkhaziev.ylab.core.logic.GameProcess;
import lombok.RequiredArgsConstructor;
import lombok.extern.slf4j.Slf4j;
import org.springframework.stereotype.Service;

@Service
@Slf4j
@RequiredArgsConstructor
public class GameService {
    private final GameProcess gameProcess;
    private final GamePlayersStorage players;
    private final ConverterPlayer converterPlayer;

    public XOResponse startNewGame(String player1, String player2) {
        gameProcess.startNewGame(player1, player2);
        XOResponse xoResponse = new XOResponse();
        xoResponse.setPlayer1(converterPlayer.playerToDTO(players.getPlayer1()));
        xoResponse.setPlayer2(converterPlayer.playerToDTO(players.getPlayer2()));
        xoResponse.setGameMap(gameProcess.getGameMap().getGameMap());
        log.warn("собран ответ XOResponse на создание новой игры");
        return xoResponse;
    }

    public XOResponse step(XORequest request) {
        XOResponse xoResponse = new XOResponse();
        xoResponse.setGameMap(gameProcess.step(request.getX(), request.getY(), converterPlayer.dtoToPlayer(request.getPlayerDTO())));
        xoResponse.setPlayer1(converterPlayer.playerToDTO(players.getPlayer1()));
        xoResponse.setPlayer2(converterPlayer.playerToDTO(players.getPlayer2()));
        return xoResponse;
    }
}
