package com.example.letscode.TesteDeIntegracaoService;

import com.example.letscode.model.Professor;
import com.example.letscode.service.ProfessorService;
import org.junit.jupiter.api.Test;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;
import javax.transaction.Transactional;
import java.util.List;
import static org.junit.jupiter.api.Assertions.*;

@SpringBootTest
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
}
