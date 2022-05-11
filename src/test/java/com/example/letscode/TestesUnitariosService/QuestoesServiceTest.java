package com.example.letscode.TestesUnitariosService;

import com.example.letscode.model.Disciplina;
import com.example.letscode.model.Professor;
import com.example.letscode.model.Questao;
import com.example.letscode.repository.QuestaoRepository;
import com.example.letscode.service.QuestaoService;
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
public class QuestoesServiceTest {

    @InjectMocks
    private QuestaoService questaoService;
    @Mock
    private QuestaoRepository questaoRepository;

    @Test
    void salvarQuestao(){

        Professor professor = new Professor("Mateus");
        Disciplina disciplina = new Disciplina("Matematica", professor);
        Questao questaoSalvar = new Questao("Teste", disciplina);
        questaoSalvar.setId(1);

        Questao questaoRetorno = new Questao("Teste", disciplina);
        questaoRetorno.setId(1);

        Mockito.when(questaoRepository.save(questaoSalvar)).thenReturn(questaoRetorno);
        questaoRetorno = questaoService.salvarQuestao(questaoSalvar);

        Assertions.assertNotNull(questaoRetorno);
        Assertions.assertEquals(questaoSalvar.getId(),questaoRetorno.getId());
        Assertions.assertEquals(questaoSalvar.getEnunciado(), questaoRetorno.getEnunciado());
        Assertions.assertEquals(questaoSalvar.getDisciplina(), questaoRetorno.getDisciplina());
        System.out.println("Id: " + questaoRetorno.getId() + " | Questão enunciado: " + questaoRetorno.getEnunciado() + " | Disciplina: " + questaoRetorno.getDisciplina().getNome() + " | Professor: " + questaoRetorno.getDisciplina().getProfessor().getNome());
    }
    @Test
    void selecionaUmaQuestao(){

        Professor professor = new Professor("José");
        Disciplina disciplina = new Disciplina("Portugês", professor);
        Questao questao = new Questao("Teste", disciplina);
        questao.setId(1);

        Mockito.when(questaoRepository.findById(1)).thenReturn(Optional.of(questao));

        Questao questao1 = questaoService.buscarQuestaoporId(1);

        Assertions.assertNotNull(questao1);
        Assertions.assertNotNull(questao1.getId());
        Assertions.assertEquals(1, questao.getId());
        System.out.println("Id: " + questao1.getId() + " | Questão enunciado: " + questao1.getEnunciado() + " | Disciplina: " + questao1.getDisciplina().getNome() + " | Professor: " + questao1.getDisciplina().getProfessor().getNome());
    }
    @Test
    void listaQuestoesTeste() {

        List<Questao> questaoList = new ArrayList<>();
        Professor professor = new Professor("Maria");
        Disciplina disciplina = new Disciplina("Filosofia", professor);
        questaoList.add(new Questao("Teste enunciado 1", disciplina));
        questaoList.add(new Questao("Teste enunciado 2", disciplina));
        questaoList.add(new Questao("Teste enunciado 3", disciplina));

        Mockito.when(questaoRepository.findAll())
                .thenReturn(questaoList);

        List<Questao> questoes = questaoService.listarQuestoes();

        Assertions.assertNotNull(questoes);
        Assertions.assertFalse(questoes.isEmpty());
        Assertions.assertEquals(3, questaoList.size());
        for (Questao questoe : questoes){
            System.out.println("Id: " + questoe.getId() + " | Questão enunciado: " + questoe.getEnunciado() + " | Disciplina: " + questoe.getDisciplina().getNome() + " | Professor: " + questoe.getDisciplina().getProfessor().getNome());
        }
    }
}
