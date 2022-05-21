package com.example.letscode.testesDeIntegracaoController;

import com.example.letscode.controller.ProfessorController;
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
public class ProfessorRestControllerIntegTest {


    @Autowired
    TestRestTemplate restTemplate;

    @MockBean
    ProfessorController professorController;



    @Test
    void listProfessorControlletIntegTest(){
        List<Professor> professorList = new ArrayList<>();
        professorList.add(new Professor("Rodrigo"));
        professorList.add(new Professor("Jessé"));
        professorList.add(new Professor("Haron"));

        HttpHeaders httpHeaders = new HttpHeaders();
        HttpEntity<Professor> httpEntity = new HttpEntity<>(httpHeaders);

        ResponseEntity<Professor[]> response = this.restTemplate
                .exchange("/professor", HttpMethod.GET, httpEntity, Professor[].class);

        Assertions.assertEquals(200, response.getStatusCodeValue());

    }


    @Test
    void salvarProfessorControllerIntegTest(){
        Professor professor = new Professor();
        professor.setId(10);
        professor.setNome("Rodrigo");

        HttpHeaders httpHeaders = new HttpHeaders();
        HttpEntity<Professor> httpEntity = new HttpEntity<>(professor, httpHeaders);

        ResponseEntity<String> response = restTemplate

                .exchange("/professor/salva", HttpMethod.POST, httpEntity, String.class);

        Assertions.assertEquals(200, response.getStatusCodeValue());

    }
    @Test
    void buscarProfessorIdControllerIntegTest(){
        Professor professor = new Professor();
        professor.setId(11);
        professor.setNome("Marcelo");

        HttpHeaders httpHeaders = new HttpHeaders();
        HttpEntity<Professor> httpEntity = new HttpEntity<>(professor, httpHeaders);

        ResponseEntity<Professor> response = restTemplate

                .exchange("/professor/11", HttpMethod.GET, httpEntity, Professor.class);

        Assertions.assertEquals(200, response.getStatusCodeValue());

    }


}
