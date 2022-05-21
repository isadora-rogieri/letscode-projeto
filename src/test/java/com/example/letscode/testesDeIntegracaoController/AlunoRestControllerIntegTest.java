package com.example.letscode.testesDeIntegracaoController;

import com.example.letscode.controller.AlunoController;
import com.example.letscode.model.Aluno;
import org.junit.jupiter.api.Assertions;
import org.junit.jupiter.api.Test;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.boot.test.web.client.TestRestTemplate;
import org.springframework.http.HttpEntity;
import org.springframework.http.HttpHeaders;
import org.springframework.http.HttpMethod;
import org.springframework.http.ResponseEntity;

import java.time.LocalDate;
import java.util.ArrayList;
import java.util.List;

@SpringBootTest(webEnvironment= SpringBootTest.WebEnvironment.RANDOM_PORT)
public class AlunoRestControllerIntegTest {

    @Autowired
    TestRestTemplate restTemplate;

    @Autowired
    AlunoController alunoController;



    @Test
    void listAlunoControlletIntegTest(){
        List<Aluno> alunoList = new ArrayList<>();
        alunoList.add(new Aluno("Amanda", "MTLA220365", LocalDate.now()));
        alunoList.add(new Aluno("Valeria", "MTLA220115", LocalDate.now()));


        HttpHeaders httpHeaders = new HttpHeaders();
        HttpEntity<Aluno> httpEntity = new HttpEntity<>(httpHeaders);

        ResponseEntity<Aluno[]> response = this.restTemplate
                .exchange("/aluno", HttpMethod.GET, httpEntity, Aluno[].class);

        Assertions.assertEquals(200, response.getStatusCodeValue());

    }


    @Test
    void salvarAlunoControllerIntegTest(){
        Aluno aluno =new Aluno("Valeria", "MTLA220100", LocalDate.now());


        HttpHeaders httpHeaders = new HttpHeaders();
        HttpEntity<Aluno> httpEntity = new HttpEntity<>(aluno, httpHeaders);

        ResponseEntity<String> response = restTemplate

                .exchange("/aluno/salva", HttpMethod.POST, httpEntity, String.class);

        Assertions.assertEquals(201, response.getStatusCodeValue());

    }
    @Test
    void buscarAlunoIdControllerIntegTest(){
        Aluno aluno =new Aluno("Valeria", "MTLA220115", LocalDate.now());
        aluno.setId(1);

        HttpHeaders httpHeaders = new HttpHeaders();
        HttpEntity<Aluno> httpEntity = new HttpEntity<>(aluno, httpHeaders);

        ResponseEntity<Aluno> response = restTemplate

                .exchange("/professor/1", HttpMethod.GET, httpEntity, Aluno.class);

        Assertions.assertEquals(200, response.getStatusCodeValue());

    }


}

