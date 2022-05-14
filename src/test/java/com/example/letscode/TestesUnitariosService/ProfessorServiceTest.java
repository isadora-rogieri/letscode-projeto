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

@ExtendWith(MockitoExtension.class)
public class ProfessorServiceTest {

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
    void salvarProfessor() {

        Mockito.when(professorRepository.save(professorSalvar)).thenReturn(professorRetorno);
        professorRetorno = professorService.salvarProfessor(professorSalvar);

        Assertions.assertNotNull(professorRetorno);
        Assertions.assertEquals(professorSalvar.getId(),professorRetorno.getId());
        Assertions.assertEquals(professorSalvar.getNome(), professorRetorno.getNome());
        System.out.println("Salvando professor Id: " + professorRetorno.getId() + " Nome: " + professorRetorno.getNome());
    }
    @Test
    void selecionaUmProfessor(){

        Mockito.when(professorRepository.findProfessorById(1)).thenReturn(Optional.of(professorSalvar));
        Professor professor1 = professorService.selecionarProfessor(1);

        Assertions.assertNotNull(professor1);
        Assertions.assertNotNull(professor1.getId());
        Assertions.assertEquals(1, professorSalvar.getId());

        System.out.println("Selecionando professor Id: " + professorSalvar.getId() + " Nome: " + professorSalvar.getNome());
    }
    @Test
    void listaProfessorTeste() {

        Mockito.when(professorRepository.findAll())
                .thenReturn(professorList);

        List<Professor> professors = professorService.listarTodos();

        Assertions.assertNotNull(professors);
        Assertions.assertFalse(professors.isEmpty());
        Assertions.assertEquals(2, professorList.size());
        System.out.println("Listando professor Id: " + professorSalvar.getId() + " Nome: " + professorSalvar.getNome());

    }
}