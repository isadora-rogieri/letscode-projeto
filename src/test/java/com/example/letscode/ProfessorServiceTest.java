package com.example.letscode;

import com.example.letscode.model.Professor;
import com.example.letscode.repository.ProfessorRepository;
import com.example.letscode.service.ProfessorService;
import org.junit.jupiter.api.Assertions;
import org.junit.jupiter.api.Test;
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

    @Test
    void salvarProfessor() {
        Professor professorSalvar = new Professor("Luiza");
        professorSalvar.setId(1);
        Professor professorRetorno = new Professor("Luiza");
        professorRetorno.setId(1);

        Mockito.when(professorRepository.save(professorSalvar)).thenReturn(professorRetorno);
        professorRetorno = professorService.salvarProfessor(professorSalvar);

        Assertions.assertNotNull(professorRetorno);
        Assertions.assertEquals(professorSalvar.getId(),professorRetorno.getId());
        Assertions.assertEquals(professorSalvar.getNome(), professorRetorno.getNome());
        System.out.println("Id: " + professorRetorno.getId() + " Nome: " + professorRetorno.getNome());
    }
    @Test
    void selecionaUmProfessor(){
        Professor professor = new Professor("Jessé");
        professor.setId(1);

        Mockito.when(professorRepository.findProfessorById(1)).thenReturn(Optional.of(professor));

        Professor professor1 = professorService.selecionarProfessor(1);

        Assertions.assertNotNull(professor1);
        Assertions.assertNotNull(professor1.getId());
        Assertions.assertEquals(1, professor.getId());

        System.out.println("Id: " + professor.getId() + " Nome: " + professor.getNome());
    }
    @Test
    void listaProfessorTeste() {

        List<Professor> professorList = new ArrayList<>();
        professorList.add(new Professor("Alan"));
        professorList.add(new Professor("José"));
        professorList.add(new Professor("Lucas"));

        Mockito.when(professorRepository.findAll())
                .thenReturn(professorList);

        List<Professor> professors = professorService.listarTodos();

        Assertions.assertNotNull(professors);
        Assertions.assertFalse(professors.isEmpty());
        Assertions.assertEquals(3, professorList.size());
    }
}
