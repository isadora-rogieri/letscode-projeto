package com.example.letscode.TestesUnitariosService;

import com.example.letscode.model.Disciplina;
import com.example.letscode.model.Professor;
import com.example.letscode.model.Questao;
import com.example.letscode.repository.QuestaoRepository;
import com.example.letscode.service.QuestaoService;
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
public class QuestoesServiceTest {

    @InjectMocks
    private QuestaoService questaoService;
    @Mock
    private QuestaoRepository questaoRepository;

    private Professor professor;
    private Disciplina disciplina;
    private Questao questaoSalvar;
    private Questao questaoRetorno;
    private Questao questaoAddList;
    private List<Questao> questaoList;

    @BeforeAll
    public static void atesDeTodos(){ // o método deve ser sempre estático
        System.out.println("Iniciando testes QuestoesServiceTest");
    }
    @AfterAll
    public static void aposDeTodos(){ // o método deve ser sempre estático
        System.out.println("Finalizando testes QuestoesServiceTest");
    }
    @BeforeEach

    public void inicializar(){
        professor = new Professor("Mateus");
        disciplina = new Disciplina("Matematica", professor);
        questaoSalvar = new Questao("Teste", disciplina);
        questaoSalvar.setId(1);
        questaoRetorno = new Questao("Teste", disciplina);
        questaoRetorno.setId(1);
        questaoAddList = new Questao("Teste 2", disciplina);
        questaoAddList.setId(2);
        questaoList = new ArrayList<>();
        questaoList.add(questaoSalvar);
        questaoList.add(questaoAddList);
    }
    @Test
    void salvarQuestao(){

        Mockito.when(questaoRepository.save(questaoSalvar)).thenReturn(questaoRetorno);
        questaoRetorno = questaoService.salvarQuestao(questaoSalvar);

        Assertions.assertNotNull(questaoRetorno);
        Assertions.assertEquals(questaoSalvar.getId(),questaoRetorno.getId());
        Assertions.assertEquals(questaoSalvar.getEnunciado(), questaoRetorno.getEnunciado());
        Assertions.assertEquals(questaoSalvar.getDisciplina(), questaoRetorno.getDisciplina());
        System.out.println(" Salvando questão Id: " + questaoRetorno.getId() + " | Questão enunciado: " + questaoRetorno.getEnunciado() + " | Disciplina: " + questaoRetorno.getDisciplina().getNome() + " | Professor: " + questaoRetorno.getDisciplina().getProfessor().getNome());
    }
    @Test
    void selecionaUmaQuestao(){

        Mockito.when(questaoRepository.findById(1)).thenReturn(Optional.of(questaoSalvar));

        Questao questao1 = questaoService.buscarQuestaoporId(1);

        Assertions.assertNotNull(questao1);
        Assertions.assertNotNull(questao1.getId());
        Assertions.assertEquals(1, questaoSalvar.getId());
        System.out.println("Selecionando questão Id: " + questao1.getId() + " | Questão enunciado: " + questao1.getEnunciado() + " | Disciplina: " + questao1.getDisciplina().getNome() + " | Professor: " + questao1.getDisciplina().getProfessor().getNome());
    }
    @Test
    void listaQuestoesTeste() {

        Mockito.when(questaoRepository.findAll()).thenReturn(questaoList);

        List<Questao> questoes = questaoService.listarQuestoes();

        Assertions.assertNotNull(questoes);
        Assertions.assertFalse(questoes.isEmpty());
        Assertions.assertEquals(2, questaoList.size());
        for (Questao questoe : questoes){
            System.out.println("Listando questão Id: " + questoe.getId() + " | Questão enunciado: " + questoe.getEnunciado() + " | Disciplina: " + questoe.getDisciplina().getNome() + " | Professor: " + questoe.getDisciplina().getProfessor().getNome());
        }
    }
}