package com.example.letscode.testesDeIntegracaoController;

import com.example.letscode.controller.DisciplinaController;
import com.example.letscode.model.Disciplina;
import com.example.letscode.model.Professor;
import org.junit.jupiter.api.Assertions;
import org.junit.jupiter.api.Test;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.boot.test.mock.mockito.MockBean;
import org.springframework.boot.test.web.client.TestRestTemplate;
import org.springframework.http.HttpEntity;
import org.springframework.http.HttpHeaders;
import org.springframework.http.HttpMethod;
import org.springframework.http.ResponseEntity;

import java.util.ArrayList;
import java.util.List;


@SpringBootTest(webEnvironment= SpringBootTest.WebEnvironment.RANDOM_PORT)
public class DisciplinaRestControllerIntegTest {


    @Autowired
    TestRestTemplate restTemplate;

    @MockBean
    DisciplinaController disciplinaController;



    @Test
    void listDisciplinaControlletIntegTest(){
        List<Disciplina> disciplinaList = new ArrayList<>();
        disciplinaList.add(new Disciplina(1, "Programação Web", new Professor("Haron")));
        disciplinaList.add(new Disciplina(2, "Testes",  new Professor("Rodrigo")));
        disciplinaList.add(new Disciplina(3, "Banco de Dados",  new Professor("Jesse")));

        HttpHeaders httpHeaders = new HttpHeaders();
        HttpEntity<Disciplina> httpEntity = new HttpEntity<>(httpHeaders);

        ResponseEntity<Disciplina[]> response = this.restTemplate
                .exchange("/disciplina", HttpMethod.GET, httpEntity, Disciplina[].class);

        Assertions.assertEquals(200, response.getStatusCodeValue());

    }



    @Test
    void buscarDisciplinaIdControllerIntegTest(){
       Disciplina disciplina= new Disciplina(2, "Testes",  new Professor("Rodrigo"));

        HttpHeaders httpHeaders = new HttpHeaders();
        HttpEntity<Disciplina> httpEntity = new HttpEntity<>(disciplina, httpHeaders);

        ResponseEntity<Disciplina> response = restTemplate

                .exchange("/disciplina/2", HttpMethod.GET, httpEntity, Disciplina.class);

        Assertions.assertEquals(200, response.getStatusCodeValue());

    }


}
