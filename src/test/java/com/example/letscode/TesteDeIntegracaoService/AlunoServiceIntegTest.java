package com.example.letscode.TesteDeIntegracaoService;

import com.example.letscode.model.Aluno;
import com.example.letscode.service.AlunoService;
import org.junit.jupiter.api.Assertions.*;
import org.junit.jupiter.api.Test;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;

import javax.transaction.Transactional;
import java.time.LocalDate;
import java.util.List;

import static org.junit.jupiter.api.Assertions.*;

@SpringBootTest
public class AlunoServiceIntegTest {

    @Autowired
    AlunoService alunoService;

    @Test
    @Transactional
    void salvarAlunoTestInteg(){
        Aluno aluno = new Aluno();
        aluno.setId(2);
        aluno.setMatricula("MTLA785834");
        aluno.setNome("Roberta");
        aluno.setDataNascimento(LocalDate.now());

        Aluno alunosalvar = alunoService.salvarAluno(aluno);

        assertNotNull(alunosalvar.getId());
        assertEquals(aluno.getNome(), alunosalvar.getNome());

    }
    @Test
    void listarAlunosTeste(){
        List<Aluno> alunos = alunoService.listarTodos();

        assertNotNull(alunos);
        assertTrue(alunos.size()>0);
    }
}
