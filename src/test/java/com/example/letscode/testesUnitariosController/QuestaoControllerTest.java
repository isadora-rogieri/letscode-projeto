package com.example.letscode.testesUnitariosController;

import com.example.letscode.controller.QuestaoController;
import com.example.letscode.dto.DtoChange;
import com.example.letscode.dto.QuestaoDto;
import com.example.letscode.model.Disciplina;
import com.example.letscode.model.Professor;
import com.example.letscode.model.Questao;
import com.example.letscode.service.QuestaoService;
import com.fasterxml.jackson.databind.ObjectMapper;
import org.hamcrest.Matchers;
import org.junit.jupiter.api.Assertions;
import org.junit.jupiter.api.Test;
import org.junit.jupiter.api.extension.ExtendWith;
import org.mockito.Mockito;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.autoconfigure.web.servlet.WebMvcTest;
import org.springframework.boot.test.mock.mockito.MockBean;
import org.springframework.http.MediaType;
import org.springframework.test.context.ContextConfiguration;
import org.springframework.test.context.junit.jupiter.SpringExtension;
import org.springframework.test.web.servlet.MockMvc;
import org.springframework.test.web.servlet.request.MockMvcRequestBuilders;
import org.springframework.test.web.servlet.result.MockMvcResultHandlers;
import org.springframework.test.web.servlet.result.MockMvcResultMatchers;

import java.util.ArrayList;
import java.util.List;

import static org.springframework.test.web.servlet.result.MockMvcResultMatchers.jsonPath;

@ContextConfiguration
@ExtendWith(SpringExtension.class)
@WebMvcTest(value = QuestaoController.class)
public class QuestaoControllerTest {

    @MockBean
    private QuestaoService questaoService;
    @MockBean
    private DtoChange dtoChange;
    @Autowired
    private MockMvc mockMvc;
    @Autowired
    private ObjectMapper objectMapper;

    @Test
    void deveRetornarListaDeTodasQuestoes() throws Exception {

        Disciplina disciplina = new Disciplina(1, "disciplina teste", new Professor("José"));
        List<Questao> listaQuestoes = new ArrayList<>();
        listaQuestoes.add(new Questao(1,"Questao 1", disciplina));
        listaQuestoes.add(new Questao(2,"Questao 2", disciplina));
        listaQuestoes.add(new Questao(3,"Questao 3", disciplina));

        Mockito.when(questaoService.listarQuestoes())
                .thenReturn(listaQuestoes);


        listaQuestoes.forEach( x -> Mockito.when(dtoChange.questaoToQuestaoDto(x))
                .thenReturn(new QuestaoDto(x.getId(), x.getEnunciado(), x.getDisciplina().getId())));


        this.mockMvc.perform(MockMvcRequestBuilders
                        .get("/questoes")
                        .accept(MediaType.APPLICATION_JSON)
                        .contentType(MediaType.APPLICATION_JSON)
                )
                .andDo(MockMvcResultHandlers.print())
                .andExpect(MockMvcResultMatchers.status().isOk())
                .andExpect(jsonPath("$", Matchers.hasSize(listaQuestoes.size())))
                .andExpect(MockMvcResultMatchers.jsonPath("$.[0].id", Matchers.is(listaQuestoes.get(0).getId()), Integer.class))
                .andExpect(MockMvcResultMatchers.jsonPath("$.[1].id", Matchers.is(listaQuestoes.get(1).getId()), Integer.class))
                .andExpect(MockMvcResultMatchers.jsonPath("$.[2].id", Matchers.is(listaQuestoes.get(2).getId()), Integer.class));
    }

    @Test
    void deveRetornarMensagemDeQuestaoSalva() throws Exception {

        Disciplina disciplina = new Disciplina(1, "disciplina teste", new Professor("José"));
        Questao questao = new Questao(1,"Questao 1", disciplina);
        QuestaoDto questaoDto = new QuestaoDto(1,"Questao 1", 1);

        Mockito.when(dtoChange.questaoDtoToQuestao(questaoDto))
                .thenReturn(questao);
        Mockito.when(questaoService.salvarQuestao(questao))
                .thenReturn(questao);

        this.mockMvc.perform(MockMvcRequestBuilders
                        .post("/questoes")
                        .accept(MediaType.APPLICATION_JSON)
                        .contentType(MediaType.APPLICATION_JSON)
                        .content(objectMapper.writeValueAsBytes(questaoDto))
                )
                .andDo(MockMvcResultHandlers.print())
                .andExpect(MockMvcResultMatchers.status().isCreated())
                .andExpect(MockMvcResultMatchers.content().string("Questao salva com sucesso!"));

        Assertions.assertEquals(questaoDto.getId(), questao.getId());
        Assertions.assertEquals(questaoDto.getEnunciado(), questao.getEnunciado());
        Assertions.assertEquals(questaoDto.getDisciplina_id(), questao.getDisciplina().getId());
    }

    @Test
    void deveRetornarMensagemDeQuestaoDeletada() throws Exception {

        Disciplina disciplina = new Disciplina(1, "disciplina teste", new Professor("José"));
        Questao questao = new Questao(1,"Questao 1", disciplina);

        Mockito.when(questaoService.buscarQuestaoporId(1))
                .thenReturn(questao);

        this.mockMvc.perform(MockMvcRequestBuilders
                        .delete("/questoes/1")

                )
                .andDo(MockMvcResultHandlers.print())
                .andExpect(MockMvcResultMatchers.status().isOk())
                .andExpect(MockMvcResultMatchers.content().string("Questão deletada com sucesso."));

        Assertions.assertEquals(1, questao.getId());
        Mockito.verify(questaoService).deletarQuestao(1);

    }

    @Test
    void deveRetornarQuestaoDtoComOId() throws Exception {

        Disciplina disciplina = new Disciplina(1, "disciplina teste", new Professor("José"));
        Questao questao = new Questao(1,"Questao 1", disciplina);
        QuestaoDto questaoDto = new QuestaoDto(1,"Questao 1", 1);

        Mockito.when(questaoService.buscarQuestaoporId(1))
                .thenReturn(questao);
        Mockito.when(dtoChange.questaoToQuestaoDto(questao))
                .thenReturn(questaoDto);

        this.mockMvc.perform(MockMvcRequestBuilders
                        .get("/questoes/1")
                        .accept(MediaType.APPLICATION_JSON)
                        .contentType(MediaType.APPLICATION_JSON)
                )
                .andDo(MockMvcResultHandlers.print())
                .andExpect(MockMvcResultMatchers.status().isOk())
                .andExpect(jsonPath("$.id", Matchers.is(questao.getId()), Integer.class))
                .andExpect(jsonPath("$.enunciado", Matchers.is(questao.getEnunciado()), String.class))
                .andExpect(jsonPath("$.disciplina_id", Matchers.is(questao.getDisciplina().getId()), Integer.class));
    }


}
