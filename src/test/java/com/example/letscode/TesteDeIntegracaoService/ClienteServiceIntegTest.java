package com.example.letscode.TesteDeIntegracaoService;

import com.example.letscode.model.Aluno;
import com.example.letscode.service.AlunoService;
import org.junit.jupiter.api.Assertions;
import org.junit.jupiter.api.Test;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;

import java.time.LocalDate;

@SpringBootTest
public class ClienteServiceIntegTest {

    @Autowired
    AlunoService alunoService;

    @Test
    void salvarAlunoTestInteg(){
        Aluno aluno = new Aluno();
        aluno.setId(1);
        aluno.setMatricula("MTLA785632");
        aluno.setNome("Juan");
        aluno.setDataNascimento(LocalDate.now());

        Aluno alunosalvar = alunoService.salvarAluno(aluno);

        Assertions.assertNotNull(alunosalvar.getId());
        Assertions.assertEquals(aluno.getNome(), alunosalvar.getNome());

    }
}
