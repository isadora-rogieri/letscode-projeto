package com.example.letscode.testeDeIntegracaoService;

import com.example.letscode.model.Professor;
import com.example.letscode.service.ProfessorService;
import org.junit.jupiter.api.Test;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;

import javax.transaction.Transactional;
import java.util.List;

import static org.junit.jupiter.api.Assertions.*;

@SpringBootTest(webEnvironment= SpringBootTest.WebEnvironment.RANDOM_PORT)
class ProfessorServiceIntegTest {

    @Autowired
    ProfessorService professorService;

    @Test
    @Transactional
    void salvarProfTestInteg(){
        Professor professor = new Professor();
        professor.setId(1);
        professor.setNome("Haron");

        Professor profSalvar = professorService.salvarProfessor(professor);

        assertNotNull(profSalvar.getId());
        assertEquals(professor.getNome(), profSalvar.getNome());

    }
    @Test
    void listarProfessorTeste(){
        List<Professor> professorList = professorService.listarTodos();

        assertNotNull(professorList);
        assertTrue(professorList.size()>0);
    }
    @Test
    void buscarProfessorTeste(){
       Professor professor1 = professorService.selecionarProfessor(1);

        assertNotNull(professor1);
        assertTrue(professor1.getId() ==1);
    }



}
