package com.egorkhaziev.ylab.core;


import com.egorkhaziev.ylab.core.DTO.PlayerDTO;
import com.egorkhaziev.ylab.core.utils.XORequest;
import com.egorkhaziev.ylab.core.utils.XOResponse;
import org.junit.jupiter.api.Test;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.boot.test.web.client.TestRestTemplate;
import org.springframework.test.context.ActiveProfiles;

import static org.assertj.core.api.Assertions.assertThat;

@SpringBootTest(webEnvironment = SpringBootTest.WebEnvironment.RANDOM_PORT)
@ActiveProfiles("test")
public class CoreApplicationTest {

    @Autowired
    private TestRestTemplate restTemplate;

    @Test
    public void registrationTest() {

        char[][] gameMap = new char[3][3];
        for (char[] line : gameMap) {
            for (int i = 0; i < line.length; i++) {
                line[i] = (char)'*';
            }
        }

        XORequest xoRequest = new XORequest();
        xoRequest.setX(2);
        xoRequest.setY(2);
        PlayerDTO playerDTO = new PlayerDTO();
        playerDTO.setId(1);
        playerDTO.setSimbol('X');
        playerDTO.setName("Maxxx");
        xoRequest.setPlayerDTO(playerDTO);

        PlayerDTO player1 = new PlayerDTO();
        PlayerDTO player2 = new PlayerDTO();
        player1.setName("Maxxx");
        player1.setSimbol('X');
        player1.setId(1);
        player2.setName("тест");
        player2.setSimbol('O');
        player2.setId(2);

        XORequest request1 = new XORequest(1,1,player1);
        XORequest request2 = new XORequest(2,1,player2);
        XORequest request3 = new XORequest(1,2,player1);
        XORequest request4 = new XORequest(2,2,player2);
        XORequest request5 = new XORequest(1,3,player1);

        XOResponse response = null;

        try {
            response = restTemplate.getForObject("/gameplay?player1=Maxxx&player2=тест", XOResponse.class);
            Thread.sleep(500);
            restTemplate.postForObject("/gameplay/step", request1, XOResponse.class);
            Thread.sleep(500);
            restTemplate.postForObject("/gameplay/step", request2, XOResponse.class);
            Thread.sleep(500);
            restTemplate.postForObject("/gameplay/step", request3, XOResponse.class);
            Thread.sleep(500);
            restTemplate.postForObject("/gameplay/step", request4, XOResponse.class);
            Thread.sleep(500);
            restTemplate.postForObject("/gameplay/step", request5, XOResponse.class);
        } catch (InterruptedException e) {
            e.printStackTrace();
        }
        
        assertThat(response).isNotNull();
        assertThat(response.getGameMap()).isEqualTo(gameMap);

    }
}
