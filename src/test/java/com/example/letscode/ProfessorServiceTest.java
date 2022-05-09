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

@ExtendWith(MockitoExtension.class)
public class ProfessorServiceTest {

    @InjectMocks
    private ProfessorService professorService;

    @Mock
    private ProfessorRepository professorRepository;

    @Test
    void salvarProfessor() {
        Professor professorSalvar = new Professor();
        professorSalvar.setNome("Luiza");

        Professor professorRetorno = new Professor();
        professorRetorno.setId(1);
        professorRetorno.setNome("Luiza");

        Mockito.when(professorRepository.save(professorSalvar)).thenReturn(professorRetorno);
        professorRetorno = professorService.salvarProfessor(professorSalvar);

        Assertions.assertNotNull(professorRetorno);
        Assertions.assertEquals(professorSalvar.getId(),professorRetorno.getId());
        Assertions.assertEquals(professorSalvar.getNome(), professorRetorno.getNome());

    }
//    @Test
//    void selecionaUmProfessor(){
//        Professor professor = new Professor();
//        professor.setId(1);
//        professor.setNome("Jessé");
//
//        Mockito.when(professorRepository.findProfessorById(1)).thenReturn(professor);
//
//        Professor professor1 = professorService.selecionarProfessor(1);
//
//        Assertions.assertNotNull(professor1);
//        Assertions.assertNotNull(professor1.getId());
//        Assertions.assertEquals(1, professor.getId());}
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
