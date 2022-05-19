package com.example.letscode.TestesUnitariosService;

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
}