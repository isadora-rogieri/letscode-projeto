package com.example.letscode.testesDeIntegracaoController;

import com.example.letscode.dto.AlternativaDto;
import com.example.letscode.model.Alternativa;
import com.example.letscode.model.Disciplina;
import com.example.letscode.model.Professor;
import com.example.letscode.model.Questao;
import org.junit.jupiter.api.Assertions;
import org.junit.jupiter.api.BeforeAll;
import org.junit.jupiter.api.Test;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.boot.test.web.client.TestRestTemplate;
import org.springframework.http.HttpEntity;
import org.springframework.http.HttpHeaders;
import org.springframework.http.HttpMethod;
import org.springframework.http.ResponseEntity;

import java.util.ArrayList;
import java.util.List;

@SpringBootTest(webEnvironment = SpringBootTest.WebEnvironment.RANDOM_PORT)
class AlternativaControllerIntegTest {

    @Autowired
    TestRestTemplate restTemplate;

    private static List<Alternativa> listaAlternativas = new ArrayList<>();
    private static List<AlternativaDto> listaAlternativasDto = new ArrayList<>();

    @BeforeAll
    static void iniciaTestes() {
        Professor professor = new Professor("Professor");
        Disciplina disciplina = new Disciplina("disciplina",professor);
        Questao questao1 = new Questao(1,"questao 1", disciplina);
        listaAlternativas.add(new Alternativa( 1,"Alternativa 1", true, questao1));
        listaAlternativas.add(new Alternativa( 2,"Alternativa 2", false, questao1));
        listaAlternativas.add(new Alternativa( 3,"Alternativa 3", false, questao1));
        listaAlternativas.add(new Alternativa( 4,"Alternativa 4", false, new Questao(2,"questao 2", disciplina)));

        listaAlternativasDto.add(new AlternativaDto( 100,"Alternativa 1", true, 1));
        listaAlternativasDto.add(new AlternativaDto( 2,"Alternativa 2", false, 1));
        listaAlternativasDto.add(new AlternativaDto( 3,"Alternativa 3", false, 1));
        listaAlternativasDto.add(new AlternativaDto( 4,"Alternativa 4", false, 2));

    }

    @Test
    void deveRetornarStatus201quandoAlternativaSalva() {

        HttpHeaders httpHeaders = new HttpHeaders();
        HttpEntity httpEntity = new HttpEntity(listaAlternativasDto.get(0), httpHeaders);

        ResponseEntity response = this.restTemplate
                .exchange("/alternativas", HttpMethod.POST, httpEntity, Void.class);

        Assertions.assertEquals(201, response.getStatusCodeValue());

    }

    @Test
    void deveRetornarStatus409quandoAlternativaJaExiste() {

        HttpHeaders httpHeaders = new HttpHeaders();
        HttpEntity httpEntity = new HttpEntity(listaAlternativasDto.get(1), httpHeaders);

        ResponseEntity response = this.restTemplate
                .exchange("/alternativas", HttpMethod.POST, httpEntity, Void.class);

        Assertions.assertEquals(409, response.getStatusCodeValue());
    }

    @Test
    void deveRetornarListaDeALternativasDto() {

        HttpHeaders httpHeaders = new HttpHeaders();
        HttpEntity httpEntity = new HttpEntity(httpHeaders);

        ResponseEntity<AlternativaDto[]> response = this.restTemplate
                .exchange("/alternativas", HttpMethod.GET, httpEntity, AlternativaDto[].class);

        Assertions.assertEquals(200, response.getStatusCodeValue());
        Assertions.assertNotNull(response.getBody());
        Assertions.assertNotEquals(0, response.getBody().length);
        Assertions.assertNotNull(response.getBody()[0].getId());

    }

    @Test
    void deveRetornar404quandoAlternativaNaoEncontrada() {

        HttpHeaders httpHeaders = new HttpHeaders();
        HttpEntity httpEntity = new HttpEntity(httpHeaders);

        ResponseEntity response = this.restTemplate
                .exchange("/alternativas/100", HttpMethod.GET, httpEntity, Void.class);

        Assertions.assertEquals(404, response.getStatusCodeValue());
    }

}