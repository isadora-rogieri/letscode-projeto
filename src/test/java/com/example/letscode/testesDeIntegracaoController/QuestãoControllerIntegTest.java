package com.example.letscode.testesDeIntegracaoController;

import com.example.letscode.dto.QuestaoDto;
import org.junit.jupiter.api.Assertions;
import org.junit.jupiter.api.Test;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.boot.test.web.client.TestRestTemplate;
import org.springframework.http.HttpEntity;
import org.springframework.http.HttpHeaders;
import org.springframework.http.HttpMethod;
import org.springframework.http.ResponseEntity;

@SpringBootTest(webEnvironment = SpringBootTest.WebEnvironment.RANDOM_PORT)
public class QuestãoControllerIntegTest {

    @Autowired
    TestRestTemplate restTemplate;

    @Test
    void deveRetornarStatus409quandoQuestaoJaExiste() {

        QuestaoDto questaoDto = new QuestaoDto(1,"Questao 1", 1);

        HttpHeaders httpHeaders = new HttpHeaders();
        HttpEntity httpEntity = new HttpEntity(questaoDto, httpHeaders);

        ResponseEntity response = this.restTemplate
                .exchange("/questoes", HttpMethod.POST, httpEntity, Void.class);

        Assertions.assertEquals(409, response.getStatusCodeValue());
    }

    @Test
    void deveRetornar404quandoQuestaoNaoEncontrada() {

        HttpHeaders httpHeaders = new HttpHeaders();
        HttpEntity httpEntity = new HttpEntity(httpHeaders);

        ResponseEntity response = this.restTemplate
                .exchange("/questoes/100", HttpMethod.GET, httpEntity, Void.class);

        Assertions.assertEquals(404, response.getStatusCodeValue());
    }
}
