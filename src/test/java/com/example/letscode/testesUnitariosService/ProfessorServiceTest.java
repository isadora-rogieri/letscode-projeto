package com.example.letscode.testesUnitariosService;

import com.example.letscode.model.Professor;
import com.example.letscode.repository.ProfessorRepository;
import com.example.letscode.service.ProfessorService;
import org.junit.jupiter.api.*;
import org.junit.jupiter.api.extension.ExtendWith;
import org.mockito.InjectMocks;
import org.mockito.Mock;
import org.mockito.Mockito;
import org.mockito.junit.jupiter.MockitoExtension;

import java.util.ArrayList;
import java.util.List;
import java.util.Optional;

import static org.junit.jupiter.api.Assertions.*;
import static org.mockito.Mockito.*;
import static org.mockito.Mockito.times;

@ExtendWith(MockitoExtension.class)
class ProfessorServiceTest {

    @InjectMocks
    private ProfessorService professorService;

    @Mock
    private ProfessorRepository professorRepository;

    private Professor professorSalvar;
    private Professor professorRetorno;
    private Professor professorAddList;
    private List<Professor> professorList;

    @BeforeAll
    public static void atesDeTodos(){ // o método deve ser sempre estático
        System.out.println("Iniciando testes ProfessorServiceTest");
    }
    @AfterAll
    public static void aposDeTodos(){ // o método deve ser sempre estático
        System.out.println("Finalizando testes ProfessorServiceTest");
    }
    @BeforeEach

    public void inicializar(){
        professorSalvar = new Professor("Jessé");
        professorSalvar.setId(1);
        professorRetorno = new Professor("Jessé");
        professorRetorno.setId(1);
        professorAddList = new Professor("Jessé");
        professorAddList.setId(1);

        professorList = new ArrayList<>();
        professorList.add(professorSalvar);
        professorList.add(professorAddList);
    }

    @Test
    @DisplayName("Teste salvar professor")
    void salvarProfessor() {

        Mockito.when(professorRepository.save(professorSalvar)).thenReturn(professorRetorno);
        professorRetorno = professorService.salvarProfessor(professorSalvar);

        assertNotNull(professorRetorno);
        assertEquals(professorSalvar.getId(),professorRetorno.getId());
        assertEquals(professorSalvar.getNome(), professorRetorno.getNome());
        System.out.println("Salvando professor Id: " + professorRetorno.getId() + " Nome: " + professorRetorno.getNome());
    }
    @Test
    @DisplayName("Teste selecionar um professor")
    void selecionaUmProfessor(){

        Mockito.when(professorRepository.findProfessorById(1)).thenReturn(Optional.of(professorSalvar));
        Professor professor1 = professorService.selecionarProfessor(1);

        assertNotNull(professor1);
        assertNotNull(professor1.getId());
        assertEquals(1, professorSalvar.getId());

        System.out.println("Selecionando professor Id: " + professorSalvar.getId() + " Nome: " + professorSalvar.getNome());
    }
    @Test
    @DisplayName("Teste selecionar professores")
    void listaProfessorTeste() {

        Mockito.when(professorRepository.findAll())
                .thenReturn(professorList);

        List<Professor> professors = professorService.listarTodos();

        assertNotNull(professors);
        assertFalse(professors.isEmpty());
        assertEquals(2, professorList.size());
        System.out.println("Listando professor Id: " + professorSalvar.getId() + " Nome: " + professorSalvar.getNome());

    }
    @Test
    @DisplayName("teste atualizar professor")
    void atualizarprofessor(){
        Professor professor1 = new Professor("Juma Marruá");
        professor1.setId(5);
        Professor professor2 = new Professor();
        professor2.setNome("Maria Marruá");
        professor2.setId(professor1.getId());

        when(professorRepository.findProfessorById(professor1.getId())).thenReturn(Optional.of(professor1));
        professorService.alterarProfessor(5, professor2);

        assertNotNull(professor1);
        assertNotNull(professor2);
        assertEquals(professor1.getId(),professor2.getId());
        assertEquals(professor1.getNome(), professor2.getNome());
    }
    @Test
    @DisplayName("Teste deletar professor")
    void deleteProfessor() {

        Professor professor = new Professor("Jove");
        professor.setId(1);


        when(professorRepository.findProfessorById(professor.getId()))
                .thenReturn(Optional.of(professor));

        doNothing().when(professorRepository).delete(professor);
        professorService.deletarProfessor(professor.getId());

        verify(professorRepository, times(1)).delete(professor);
    }

}