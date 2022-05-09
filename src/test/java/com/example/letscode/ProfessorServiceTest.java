package com.example.letscode;

import com.example.letscode.model.Aluno;
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

import java.time.LocalDate;

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
}
