package com.example.letscode.testesUnitariosService;


import com.example.letscode.model.Disciplina;
import com.example.letscode.model.Professor;
import com.example.letscode.repository.DisciplinaRepository;
import com.example.letscode.service.DisciplinaService;
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
class DisciplinaServiceTest {

    @InjectMocks
    private DisciplinaService disciplinaService;

    @Mock
    private DisciplinaRepository disciplinaRepository;

    private Disciplina disciplina;
    private Disciplina disciplina2;
    private Disciplina disciplinaRetorno;
    private List<Disciplina> disciplinaList;

    @BeforeAll
    public static void antesDeTodos(){ // o método deve ser sempre estático
        System.out.println("Iniciando testes DisciplinaServiceTest");
    }
    @AfterAll
    public static void aposDeTodos(){ // o método deve ser sempre estático
        System.out.println("Finalizando testes DisciplinaServiceTest");
    }

    @BeforeEach
    public void inicializar(){
        disciplina = new Disciplina("Programação Web", (new Professor("Haron")));
        disciplina.setId(2);
        disciplinaRetorno = new Disciplina("Programação Web", (new Professor("Haron")));
        disciplina.setId(2);
        disciplina2 = new Disciplina("Programação Web II", (new Professor("Haron")));
        disciplina2.setId(1);
        disciplinaList = new ArrayList<>();
        disciplinaList.add(disciplina);
        disciplinaList.add(disciplina2);

    }

    @Test
    @DisplayName("Teste salvar disciplina")
    void salvarDisciplina(){

        Mockito.when(disciplinaRepository.save(disciplina)).thenReturn(disciplinaRetorno);
        disciplinaRetorno = disciplinaService.salvarDisciplina(disciplina);

        assertNotNull(disciplinaRetorno);
        assertEquals(disciplina.getId(), disciplinaRetorno.getId());
        assertEquals(disciplina.getNome(), disciplinaRetorno.getNome());
        assertEquals(disciplina.getProfessor(), disciplinaRetorno.getProfessor());
    }
    @Test
    @DisplayName("Teste selecionar disciplina")
    void selecionaDisciplina(){

        Mockito.when(disciplinaRepository.findDisciplinaById(2)).thenReturn(Optional.of(disciplina));

        Disciplina d = disciplinaService.selecionarDisciplina(2);
        assertNotNull(d);
        assertEquals(disciplina.getId(), d.getId());
    }
    @Test
    @DisplayName("Teste selecionar todas as disciplinas")
    void listarTodas(){

        Mockito.when(disciplinaRepository.findAll())
              .thenReturn(disciplinaList);

        List<Disciplina> disciplinas = disciplinaService.listarTodos();

        assertNotNull(disciplinas);
        assertFalse(disciplinas.isEmpty());
        assertEquals(2, disciplinas.size());

        for (Disciplina disciplina : disciplinas){
          System.out.println("Id: " + disciplina.getId() + " | Disciplina: " + disciplina.getNome() + " | Professor: " + disciplina.getProfessor().getNome());

        }
  }
    @Test
    @DisplayName("Teste deletar disciplina")
    void deleteDisciplina() {

        when(disciplinaRepository.findDisciplinaById(disciplina2.getId()))
                .thenReturn(Optional.of(disciplina2));

        doNothing().when(disciplinaRepository).delete(disciplina2);
        disciplinaService.deletarDisciplina(disciplina2.getId());

        verify(disciplinaRepository, times(1)).delete(disciplina2);

    }
    @Test
    @DisplayName("teste atualizar aluno")
    void atualizarDisciplina(){
        Disciplina disciplina1 = new Disciplina(2, "Artes", new Professor("Jove"));
        disciplina1.setId(5);
        Disciplina disciplina2 = new Disciplina();
        disciplina2.setNome("Inglês");
        disciplina2.setProfessor(disciplina1.getProfessor());
        disciplina2.setId(disciplina1.getId());

        when(disciplinaRepository.findDisciplinaById(disciplina1.getId())).thenReturn(Optional.of(disciplina1));
        disciplinaService.alterarDisciplina(5, disciplina2);

        assertNotNull(disciplina2);
        assertNotNull(disciplina1);
        assertEquals(disciplina1.getId(),disciplina2.getId());
        assertEquals(disciplina1.getNome(), disciplina2.getNome());
        assertEquals(disciplina1.getProfessor(), disciplina2.getProfessor());
    }
}