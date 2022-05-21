package com.example.letscode.testesUnitariosService;

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

import static org.junit.jupiter.api.Assertions.*;

@ExtendWith(MockitoExtension.class)
class QuestoesServiceTest {

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
    @DisplayName("Teste salvar questão")
    void salvarQuestao(){

        Mockito.when(questaoRepository.save(questaoSalvar)).thenReturn(questaoRetorno);
        questaoRetorno = questaoService.salvarQuestao(questaoSalvar);

        assertNotNull(questaoRetorno);
        assertEquals(questaoSalvar.getId(),questaoRetorno.getId());
        assertEquals(questaoSalvar.getEnunciado(), questaoRetorno.getEnunciado());
        assertEquals(questaoSalvar.getDisciplina(), questaoRetorno.getDisciplina());
        System.out.println(" Salvando questão Id: " + questaoRetorno.getId() + " | Questão enunciado: " + questaoRetorno.getEnunciado() + " | Disciplina: " + questaoRetorno.getDisciplina().getNome() + " | Professor: " + questaoRetorno.getDisciplina().getProfessor().getNome());
    }
    @Test
    @DisplayName("Teste selecionar uma questão")
    void selecionaUmaQuestao(){

        Mockito.when(questaoRepository.findById(1)).thenReturn(Optional.of(questaoSalvar));

        Questao questao1 = questaoService.buscarQuestaoporId(1);

        assertNotNull(questao1);
        assertNotNull(questao1.getId());
        assertEquals(1, questaoSalvar.getId());
        System.out.println("Selecionando questão Id: " + questao1.getId() + " | Questão enunciado: " + questao1.getEnunciado() + " | Disciplina: " + questao1.getDisciplina().getNome() + " | Professor: " + questao1.getDisciplina().getProfessor().getNome());
    }
    @Test
    @DisplayName("Teste listar questões")
    void listaQuestoesTeste() {

        Mockito.when(questaoRepository.findAll()).thenReturn(questaoList);

        List<Questao> questoes = questaoService.listarQuestoes();

        assertNotNull(questoes);
        assertFalse(questoes.isEmpty());
        assertEquals(2, questaoList.size());
        for (Questao questoe : questoes){
            System.out.println("Listando questão Id: " + questoe.getId() + " | Questão enunciado: " + questoe.getEnunciado() + " | Disciplina: " + questoe.getDisciplina().getNome() + " | Professor: " + questoe.getDisciplina().getProfessor().getNome());
        }
    }
}