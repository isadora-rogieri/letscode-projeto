package com.example.letscode;


import com.example.letscode.model.Disciplina;
import com.example.letscode.model.Professor;
import com.example.letscode.repository.DisciplinaRepository;
import com.example.letscode.service.DisciplinaService;
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
public class DisciplinaServiceTest {

    @InjectMocks
    private DisciplinaService disciplinaService;

    @Mock
    private DisciplinaRepository disciplinaRepository;

   @Test
    void selecionaDisciplina(){
        Disciplina disciplina = new Disciplina();
        disciplina.setId(2);
        disciplina.setNome("Programação Web");
        disciplina.setProfessor(new Professor("Haron"));
        Mockito.when(disciplinaRepository.findDisciplinaById(2)).thenReturn(Optional.of(disciplina));

        Disciplina d = disciplinaService.selecionarDisciplina(2);
       Assertions.assertNotNull(d);
        Assertions.assertEquals(disciplina.getId(), d.getId());
    }
  @Test
  void listarTodas(){
      Disciplina disciplina1 = new Disciplina();
      disciplina1.setId(2);
      disciplina1.setNome("Programação Web");
      disciplina1.setProfessor(new Professor("Haron"));
      Disciplina disciplina2 = new Disciplina();
      disciplina2.setId(1);
      disciplina2.setNome("Programação Web II");
      disciplina2.setProfessor(new Professor("Haron"));
      List<Disciplina> disciplinaList = new ArrayList<>();
      disciplinaList.add(disciplina1);
      disciplinaList.add(disciplina2);

      Mockito.when(disciplinaRepository.findAll())
              .thenReturn(disciplinaList);

      List<Disciplina> disciplinas = disciplinaService.listarTodos();

      Assertions.assertNotNull(disciplinas);
      Assertions.assertFalse(disciplinas.isEmpty());
      Assertions.assertEquals(2, disciplinas.size());

  }


}
