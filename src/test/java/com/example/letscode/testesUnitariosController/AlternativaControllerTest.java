package com.example.letscode.testesUnitariosController;

import com.example.letscode.controller.AlternativaController;
import com.example.letscode.dto.AlternativaDto;
import com.example.letscode.dto.DtoChange;
import com.example.letscode.model.Alternativa;
import com.example.letscode.model.Disciplina;
import com.example.letscode.model.Professor;
import com.example.letscode.model.Questao;
import com.example.letscode.service.AlternativaService;
import com.fasterxml.jackson.databind.ObjectMapper;
import org.hamcrest.Matchers;
import org.junit.jupiter.api.Assertions;
import org.junit.jupiter.api.BeforeAll;
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
import java.util.stream.Collectors;

import static org.springframework.test.web.servlet.result.MockMvcResultMatchers.jsonPath;

@ContextConfiguration
@ExtendWith(SpringExtension.class)
@WebMvcTest(value = AlternativaController.class)
class AlternativaControllerTest {

    @MockBean
    private AlternativaService alternativaService;
    @Autowired
    private MockMvc mockMvc;
    @MockBean
    private DtoChange dtoChange;
    @Autowired
    private ObjectMapper objectMapper;

    private static final String ALTERNATIVA_SALVA_MESSAGE = "Alternativa salva com sucesso!";
    private static final String ALTERNATIVA_ATUALIZADA_MESSAGE = "Alternativa atualizada com sucesso";
    private static final String ALTERNATIVA_DELETADA_MESSAGE = "Alternativa deletada com sucesso";

    private static List<Alternativa> listaAlternativas = new ArrayList<>();
    private static List<AlternativaDto> listaAlternativasDto = new ArrayList<>();

    @BeforeAll
    private static void beforeAll() {
        Professor professor = new Professor("Professor");
        Disciplina disciplina = new Disciplina("disciplina",professor);
        Questao questao1 = new Questao(1,"questao 1", disciplina);
        listaAlternativas.add(new Alternativa( 1,"Alternativa 1", true, questao1));
        listaAlternativas.add(new Alternativa( 2,"Alternativa 2", false, questao1));
        listaAlternativas.add(new Alternativa( 3,"Alternativa 3", false, questao1));
        listaAlternativas.add(new Alternativa( 4,"Alternativa 4", false, new Questao(2,"questao 2", disciplina)));
        listaAlternativas.add(new Alternativa( 5,"Alternativa 5", false, new Questao(3,"questao 3", disciplina)));

        listaAlternativasDto.add(new AlternativaDto( 1,"Alternativa 1", true, 1));
        listaAlternativasDto.add(new AlternativaDto( 2,"Alternativa 2", false, 1));
        listaAlternativasDto.add(new AlternativaDto( 3,"Alternativa 3", false, 1));
        listaAlternativasDto.add(new AlternativaDto( 4,"Alternativa 4", false, 2));
        listaAlternativasDto.add(new AlternativaDto( 5,"Alternativa 5", false, 3));

    }

    @Test
    void deveRetornarListadeTodasAlternativasDto() throws Exception {

        Mockito.when(alternativaService.listarAlternativas())
                .thenReturn(listaAlternativas);
        for(int i=0 ; i <= 4; i++){
            Mockito.when(dtoChange.alternativaToAlternativaDto(listaAlternativas.get(i)))
                    .thenReturn(listaAlternativasDto.get(i));
        }

        this.mockMvc.perform(MockMvcRequestBuilders
                        .get("/alternativas")
                        .accept(MediaType.APPLICATION_JSON)
                        .contentType(MediaType.APPLICATION_JSON)
                )
                .andDo(MockMvcResultHandlers.print())
                .andExpect(MockMvcResultMatchers.status().isOk())
                .andExpect(jsonPath("$", Matchers.hasSize(listaAlternativas.size())))
                .andExpect(jsonPath("$.[0].id", Matchers.is(listaAlternativas.get(0).getId()), Integer.class));
    }

    @Test
    void deveRetornarAlternativaDtoComOId() throws Exception {

        Mockito.when(alternativaService.buscarAlternativaPorId(1))
                .thenReturn(listaAlternativas.get(0));
        Mockito.when(dtoChange.alternativaToAlternativaDto(listaAlternativas.get(0)))
                .thenReturn(listaAlternativasDto.get(0));

        this.mockMvc.perform(MockMvcRequestBuilders
                        .get("/alternativas/1")
                        .accept(MediaType.APPLICATION_JSON)
                        .contentType(MediaType.APPLICATION_JSON)
                )
                .andDo(MockMvcResultHandlers.print())
                .andExpect(MockMvcResultMatchers.status().isOk())
                .andExpect(jsonPath("$.id", Matchers.is(listaAlternativas.get(0).getId()), Integer.class))
                .andExpect(jsonPath("$.descricao", Matchers.is(listaAlternativas.get(0).getDescricao()), String.class))
                .andExpect(jsonPath("$.ehResposta", Matchers.is(listaAlternativas.get(0).getEhResposta()), Boolean.class))
                .andExpect(jsonPath("$.questao_id", Matchers.is(listaAlternativas.get(0).getQuestao().getId()), Integer.class));
    }

    @Test
    void deveRetornarMensagemDeAlternativaSalva() throws Exception {

        Alternativa alternativa = listaAlternativas.get(0);
        AlternativaDto alternativaDto = listaAlternativasDto.get(0);

        Mockito.when(alternativaService.salvarAlternativa(alternativa))
                .thenReturn(alternativa);
        Mockito.when(dtoChange.alternativaDtoToAlternativa(alternativaDto))
                .thenReturn(alternativa);

        this.mockMvc.perform(MockMvcRequestBuilders
                        .post("/alternativas")
                        .accept(MediaType.APPLICATION_JSON)
                        .contentType(MediaType.APPLICATION_JSON)
                        .content(objectMapper.writeValueAsBytes(alternativaDto))
                )
                .andDo(MockMvcResultHandlers.print())
                .andExpect(MockMvcResultMatchers.status().isCreated())
                .andExpect(MockMvcResultMatchers.content().string(ALTERNATIVA_SALVA_MESSAGE));

        Assertions.assertEquals(alternativaDto.getId(), alternativa.getId());
        Assertions.assertEquals(alternativaDto.getDescricao(), alternativa.getDescricao());
        Assertions.assertEquals(alternativaDto.getEhResposta(), alternativa.getEhResposta());
        Assertions.assertEquals(alternativaDto.getQuestao_id(), alternativa.getQuestao().getId());
    }

    @Test
    void deveRetornarListaDeAlternativasDtoComQuestaoId() throws Exception {

        List<Alternativa> alternativas = listaAlternativas.stream()
                .filter(a -> a.getQuestao().getId().equals(1))
                .collect(Collectors.toList());

        Mockito.when(alternativaService.buscarAlternativasPorQuestaoId(1))
                .thenReturn(alternativas);
        for(int i=0 ; i <= 2; i++){
            Mockito.when(dtoChange.alternativaToAlternativaDto(listaAlternativas.get(i)))
                    .thenReturn(listaAlternativasDto.get(i));
        }

        this.mockMvc.perform(MockMvcRequestBuilders
                        .get("/alternativas/questao/1")
                        .accept(MediaType.APPLICATION_JSON)
                        .contentType(MediaType.APPLICATION_JSON)
                )
                .andDo(MockMvcResultHandlers.print())
                .andExpect(MockMvcResultMatchers.status().isOk())
                .andExpect(jsonPath("$", Matchers.hasSize(alternativas.size())))
                .andExpect(jsonPath("$.[0].questao_id", Matchers.is(1), Integer.class))
                .andExpect(jsonPath("$.[1].questao_id", Matchers.is(1), Integer.class))
                .andExpect(jsonPath("$.[2].questao_id", Matchers.is(1), Integer.class));
    }

    @Test
    void deveRetornarMensagemDeAlternativaAtualizada() throws Exception {

        Alternativa alternativa = listaAlternativas.get(4);
        AlternativaDto alternativaDto = listaAlternativasDto.get(4);

        Mockito.when(dtoChange.alternativaDtoToAlternativa(alternativaDto))
                .thenReturn(alternativa);
        Mockito.when(alternativaService.atualizarAlternativa(5,alternativa))
                .thenReturn(alternativa);

        this.mockMvc.perform(MockMvcRequestBuilders
                        .put("/alternativas/5")
                        .accept(MediaType.APPLICATION_JSON)
                        .contentType(MediaType.APPLICATION_JSON)
                        .content(objectMapper.writeValueAsBytes(alternativaDto))
                )
                .andDo(MockMvcResultHandlers.print())
                .andExpect(MockMvcResultMatchers.status().isOk())
                .andExpect(MockMvcResultMatchers.content().string(ALTERNATIVA_ATUALIZADA_MESSAGE));

        Assertions.assertEquals(alternativaDto.getId(), alternativa.getId());
        Assertions.assertEquals(alternativaDto.getDescricao(), alternativa.getDescricao());
        Assertions.assertEquals(alternativaDto.getEhResposta(), alternativa.getEhResposta());
        Assertions.assertEquals(alternativaDto.getQuestao_id(), alternativa.getQuestao().getId());
    }

    @Test
    void deveRetornarMensagemDeAlternativaDeletada() throws Exception {

        Alternativa alternativa = listaAlternativas.get(4);

        Mockito.when(alternativaService.buscarAlternativaPorId(5))
                .thenReturn(alternativa);

        this.mockMvc.perform(MockMvcRequestBuilders
                        .delete("/alternativas/5")

                )
                .andDo(MockMvcResultHandlers.print())
                .andExpect(MockMvcResultMatchers.status().isOk())
                .andExpect(MockMvcResultMatchers.content().string(ALTERNATIVA_DELETADA_MESSAGE));

        Assertions.assertEquals(5, alternativa.getId());
        Mockito.verify(alternativaService).deletarAlternativa(5);

    }


}
