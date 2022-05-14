package com.example.letscode.TestesUnitariosService;

import com.example.letscode.model.Aluno;
import com.example.letscode.repository.AlunoRepository;
import com.example.letscode.service.AlunoService;
import org.junit.jupiter.api.*;
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

    private Aluno alunoSalvar;
    private Aluno alunoRetorno;
    private Aluno alunoAddList;

    private List<Aluno> alunoList;

    @BeforeAll
    public static void atesDeTodos(){ // o método deve ser sempre estático
        System.out.println("Iniciando testes AlunoServiceTest");
    }
    @AfterAll
    public static void aposDeTodos(){ // o método deve ser sempre estático
        System.out.println("Finalizando testes AlunoServiceTest");
    }

    @BeforeEach
    public void inicializar(){

        alunoSalvar = new Aluno("Isadora", "20220415", LocalDate.of(1995,9,15));
        alunoSalvar.setId(1);

        alunoRetorno = new Aluno("Isadora", "20220415", LocalDate.of(1995,9,15));
        alunoRetorno.setId(1);

        alunoAddList = new Aluno("Flora", "27220415", LocalDate.of(1999,9,15));
        alunoAddList.setId(2);

        alunoList = new ArrayList<>();
        alunoList.add(alunoSalvar);
        alunoList.add(alunoAddList);

    }

    @Test
    void salvarAluno() {

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

        Mockito.when(alunoRepository.findAlunoById(1)).thenReturn(Optional.of(alunoSalvar));
        Aluno alunoSelecionado = alunoService.selecionarAlunoById(1);

        Assertions.assertNotNull(alunoSelecionado);
        Assertions.assertNotNull(alunoSelecionado.getId());
        Assertions.assertEquals(1, alunoSelecionado.getId());

        System.out.println("Selecionando aluno Id: " + alunoSelecionado.getId() + " | Nome: " + alunoSelecionado.getNome() + " | Matricula: " + alunoSelecionado.getMatricula() + " | Data de Nascimento: " + alunoSelecionado.getDataNascimento());

    }
    @Test
    void listaAlunosTeste() {

        Mockito.when(alunoRepository.findAll()).thenReturn(alunoList);

        List<Aluno> alunos = alunoService.listarTodos();

        Assertions.assertNotNull(alunos);
        Assertions.assertFalse(alunos.isEmpty());
        Assertions.assertEquals(2, alunoList.size());
        for ( Aluno aluno : alunos){
            System.out.println("Listando aluno Id: " + aluno.getId() + " | Nome: " + aluno.getNome() + " | Matricula: " + aluno.getMatricula() + " | Data de Nascimento: " + aluno.getDataNascimento());

        }
    }
}
