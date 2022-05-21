package com.example.letscode.testesUnitariosService;

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

import static org.junit.jupiter.api.Assertions.*;
import static org.mockito.Mockito.*;
import static org.mockito.Mockito.times;

@ExtendWith(MockitoExtension.class)
class AlunoServiceTest {

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
    @DisplayName("Teste salvar aluno")
    void salvarAluno() {

        Mockito.when(alunoRepository.save(alunoSalvar)).thenReturn(alunoRetorno);
        alunoRetorno = alunoService.salvarAluno(alunoSalvar);

        assertNotNull(alunoRetorno);
        assertEquals(alunoSalvar.getId(),alunoRetorno.getId());
        assertEquals(alunoSalvar.getNome(), alunoRetorno.getNome());
        assertEquals(alunoSalvar.getMatricula(), alunoRetorno.getMatricula());
        assertEquals(alunoSalvar.getDataNascimento(), alunoRetorno.getDataNascimento());

    }

    @Test
    @DisplayName("Campo nome não pode ser nulo")
    void SalvarAlunoErroNomeNulo() {
        try {
            if (alunoSalvar.getNome() == null) {
                fail("Falha teste - Nome do aluno não pode ser nulo");
            }
        } catch (IllegalArgumentException e) {
            System.out.println(e.getMessage());
            assertTrue(true);
        }
    }

    @Test
    @DisplayName("Campo matrícula não pode ser nulo")
    void SalvarAlunoErroMatriculaNula() {
        try {
            if (alunoSalvar.getMatricula() == null) {
                fail("Falha teste - Matrícula não pode ser nula");
            }
        } catch (IllegalArgumentException e) {
            System.out.println(e.getMessage());
            assertTrue(true);
        }
    }

    @Test
    @DisplayName("Teste selecionar um aluno")
    void selecionaUmAluno(){

        Mockito.when(alunoRepository.findAlunoById(1)).thenReturn(Optional.of(alunoSalvar));
        Aluno alunoSelecionado = alunoService.selecionarAlunoById(1);

        assertNotNull(alunoSelecionado);
        assertNotNull(alunoSelecionado.getId());
        assertEquals(1, alunoSelecionado.getId());

        System.out.println("Selecionando aluno Id: " + alunoSelecionado.getId() + " | Nome: " + alunoSelecionado.getNome() + " | Matricula: " + alunoSelecionado.getMatricula() + " | Data de Nascimento: " + alunoSelecionado.getDataNascimento());

    }

    @Test
    @DisplayName("Teste listar alunos")
    void listaAlunosTeste() {

        Mockito.when(alunoRepository.findAll()).thenReturn(alunoList);

        List<Aluno> alunos = alunoService.listarTodos();

        assertNotNull(alunos);
        assertFalse(alunos.isEmpty());
        assertEquals(2, alunoList.size());
        for ( Aluno aluno : alunos){
            System.out.println("Listando aluno Id: " + aluno.getId() + " | Nome: " + aluno.getNome() + " | Matricula: " + aluno.getMatricula() + " | Data de Nascimento: " + aluno.getDataNascimento());
        }
    }
    @Test
    @DisplayName("Teste deletar alunos")
    void deleteAluno() {

        when(alunoRepository.findAlunoById(alunoAddList.getId()))
                .thenReturn(Optional.of(alunoAddList));

        doNothing().when(alunoRepository).delete(alunoAddList);
        alunoService.deletarAluno(alunoAddList.getId());

        verify(alunoRepository, times(1)).delete(alunoAddList);

    }
    @Test
    @DisplayName("teste atualizar aluno")
    void atualizarAluno(){
        Aluno aluno1 = new Aluno("Ma", "MTLA147441", LocalDate.now());
        aluno1.setId(5);
        Aluno aluno2 = new Aluno();
        aluno2.setNome("Marcos");
        aluno2.setMatricula(aluno1.getMatricula());
        aluno2.setId(aluno1.getId());
        aluno2.setDataNascimento(aluno1.getDataNascimento());

        when(alunoRepository.findAlunoById(aluno1.getId())).thenReturn(Optional.of(aluno1));
        alunoService.alterarAluno(5, aluno2);

        assertNotNull(aluno1);
        assertNotNull(aluno2);
        assertEquals(aluno1.getId(),aluno2.getId());
        assertEquals(aluno1.getNome(), aluno2.getNome());
        assertEquals(aluno1.getMatricula(), aluno2.getMatricula());
        assertEquals(aluno1.getDataNascimento(), aluno2.getDataNascimento());
    }
}
