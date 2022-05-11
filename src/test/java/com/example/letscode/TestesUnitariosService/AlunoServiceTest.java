package com.example.letscode.TestesUnitariosService;

import com.example.letscode.model.Aluno;
import com.example.letscode.repository.AlunoRepository;
import com.example.letscode.service.AlunoService;
import org.junit.jupiter.api.Assertions;
import org.junit.jupiter.api.Test;
import org.junit.jupiter.api.extension.ExtendWith;
import org.mockito.InjectMocks;
import org.mockito.Mock;
import org.mockito.Mockito;
import org.mockito.junit.jupiter.MockitoExtension;

import java.time.LocalDate;
import java.util.ArrayList;
import java.util.List;
import java.util.Optional;

@ExtendWith(MockitoExtension.class)
public class AlunoServiceTest {

    @InjectMocks
    private AlunoService alunoService;

    @Mock
    private AlunoRepository alunoRepository;

    @Test
    void salvarAluno() {
        Aluno alunoSalvar = new Aluno();
        alunoSalvar.setNome("Isadora");
        alunoSalvar.setMatricula("20220415");
        alunoSalvar.setDataNascimento(LocalDate.of(1995,9,15));

        Aluno alunoRetorno = new Aluno();
        alunoRetorno.setId(8);
        alunoRetorno.setNome("Isadora");
        alunoRetorno.setMatricula("20220415");
        alunoRetorno.setDataNascimento(LocalDate.of(1995,9,15));

        Mockito.when(alunoRepository.save(alunoSalvar)).thenReturn(alunoRetorno);
        alunoRetorno = alunoService.salvarAluno(alunoSalvar);

        Assertions.assertNotNull(alunoRetorno);
        Assertions.assertEquals(alunoSalvar.getId(),alunoRetorno.getId());
        Assertions.assertEquals(alunoSalvar.getNome(), alunoRetorno.getNome());
        Assertions.assertEquals(alunoSalvar.getMatricula(), alunoRetorno.getMatricula());
        Assertions.assertEquals(alunoSalvar.getDataNascimento(), alunoRetorno.getDataNascimento());

    }
    @Test
    void selecionaUmAluno(){
        Aluno aluno = new Aluno("Isadora", "20220415", LocalDate.of(1995,9,15));
        aluno.setId(8);

        Mockito.when(alunoRepository.findAlunoById(8)).thenReturn(Optional.of(aluno));

        Aluno aluno1 = alunoService.selecionarAlunoById(8);

        Assertions.assertNotNull(aluno1);
        Assertions.assertNotNull(aluno1.getId());
        Assertions.assertEquals(8, aluno.getId());

        System.out.println("Id: " + aluno.getId() + " | Nome: " + aluno.getNome() + " | Matricula: " + aluno.getMatricula() + " | Data de Nascimento: " + aluno.getDataNascimento());

    }
    @Test
    void listaAlunosTeste() {

        List<Aluno> alunoList = new ArrayList<>();
        alunoList.add(new Aluno("Alan", "MTLA125478", LocalDate.now()));
        alunoList.add(new Aluno("Mateus", "MTLA125471", LocalDate.now()));
        alunoList.add(new Aluno("João", "MTLA125479", LocalDate.now()));

        Mockito.when(alunoRepository.findAll())
                .thenReturn(alunoList);

        List<Aluno> alunos = alunoService.listarTodos();

        Assertions.assertNotNull(alunos);
        Assertions.assertFalse(alunos.isEmpty());
        Assertions.assertEquals(3, alunoList.size());
        for ( Aluno aluno : alunos){
            System.out.println("Id: " + aluno.getId() + " | Nome: " + aluno.getNome() + " | Matricula: " + aluno.getMatricula() + " | Data de Nascimento: " + aluno.getDataNascimento());

        }
    }
}
