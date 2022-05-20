package com.example.letscode.TestesUnitariosController;

import com.example.letscode.controller.DisciplinaController;
import com.example.letscode.dto.DisciplinaDto;
import com.example.letscode.dto.DtoChange;
import com.example.letscode.model.Disciplina;
import com.example.letscode.model.Professor;
import com.example.letscode.service.DisciplinaService;
import com.fasterxml.jackson.databind.ObjectMapper;
import org.junit.jupiter.api.Test;
import org.junit.jupiter.api.extension.ExtendWith;
import org.mockito.Mockito;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.autoconfigure.web.servlet.WebMvcTest;
import org.springframework.boot.test.mock.mockito.MockBean;
import org.springframework.http.MediaType;
import org.springframework.security.test.context.support.WithMockUser;
import org.springframework.test.context.ContextConfiguration;
import org.springframework.test.context.junit.jupiter.SpringExtension;
import org.springframework.test.web.servlet.MockMvc;
import org.springframework.test.web.servlet.request.MockMvcRequestBuilders;
import org.springframework.test.web.servlet.result.MockMvcResultHandlers;
import org.springframework.test.web.servlet.result.MockMvcResultMatchers;

import java.util.ArrayList;
import java.util.List;

import static org.junit.jupiter.api.Assertions.*;

@ContextConfiguration
@ExtendWith(SpringExtension.class)
@WebMvcTest(value = DisciplinaController.class)
public class DisciplinaControllerTest {

    @MockBean
    private DisciplinaService disciplinaService;

    @MockBean
    private DisciplinaDto disciplinaDto;

    @Autowired
    private MockMvc mockMvc;

    @Autowired
    private ObjectMapper objectMapper;

    @MockBean
    private DtoChange dtoChange;


    @Test
    @WithMockUser
    void salvarDisciplinaTestController () throws Exception {

        DisciplinaDto disciplinaDto = new DisciplinaDto(1, "Programação Web", 2);
        Disciplina disciplina = new Disciplina();
        Professor professor = new Professor("Haron");
        professor.setId(disciplinaDto.getProfessor_id());
        disciplina.setNome(disciplinaDto.getNome());
        disciplina.setProfessor(professor);
        disciplina.setId(disciplinaDto.getId());



        Mockito.when(disciplinaService.salvarDisciplina(disciplina)).thenReturn(disciplina);


        this.mockMvc.perform(
                        MockMvcRequestBuilders.post("/disciplina/salva")
                                .accept(MediaType.APPLICATION_JSON)
                                .contentType(MediaType.APPLICATION_JSON)
                                .content(objectMapper.writeValueAsBytes(disciplinaDto))
                ).andDo(MockMvcResultHandlers.print())
                .andExpect(MockMvcResultMatchers.status().isCreated());


        assertEquals(disciplinaDto.getProfessor_id(), disciplina.getProfessor().getId());
        assertEquals(disciplinaDto.getNome(), disciplina.getNome());
        assertEquals(disciplinaDto.getId(),disciplina.getId());


    }
    @Test
    @WithMockUser
    void listarDisciplinas() throws Exception {

        List<Disciplina> disciplinaList = new ArrayList<>();
        disciplinaList.add(new Disciplina(1, "Programação Web", new Professor("Haron")));
        disciplinaList.add(new Disciplina(2, "Testes",  new Professor("Rodrigo")));
        disciplinaList.add(new Disciplina(3, "Banco de Dados",  new Professor("Jesse")));

        Mockito.when(disciplinaService.listarTodos()).thenReturn(disciplinaList);

        this.mockMvc.perform(
                        MockMvcRequestBuilders.get("/disciplina")
                                .accept(MediaType.APPLICATION_JSON)
                                .contentType(MediaType.APPLICATION_JSON))
                .andDo(MockMvcResultHandlers.print())
                .andExpect(MockMvcResultMatchers.status().isOk());

        assertTrue( disciplinaList.size() == 3);
        assertFalse( disciplinaList.isEmpty());


    }
    @Test
    @WithMockUser
    void buscarDisciplina() throws Exception {


        Disciplina disciplina = new Disciplina();
        disciplina.setId(1);
        disciplina.setNome("Testes Automatizados");
        disciplina.setProfessor(new Professor("Rodrigo"));


        Mockito.when(disciplinaService.selecionarDisciplina(disciplina.getId())).thenReturn(disciplina);
        this.mockMvc.perform(
                        MockMvcRequestBuilders
                                .get("/disciplina/1")
                                .accept(MediaType.APPLICATION_JSON).accept(MediaType.APPLICATION_JSON)

                )
                .andDo(MockMvcResultHandlers.print())
                .andExpect(MockMvcResultMatchers.status().isOk());

    }
    @Test
    @WithMockUser
    void deletarDisciplina() throws Exception {


        Disciplina disciplina = new Disciplina();
        disciplina.setId(2);
        disciplina.setNome("Testes Automatizados");
        disciplina.setProfessor(new Professor("Rodrigo"));

        Mockito.when(disciplinaService.deletarDisciplina(disciplina.getId())).thenReturn(disciplina);

        this.mockMvc.perform(
                        MockMvcRequestBuilders
                                .delete("/disciplina/2")
                                .accept(MediaType.APPLICATION_JSON).accept(MediaType.APPLICATION_JSON)

                )
                .andDo(MockMvcResultHandlers.print())
                .andExpect(MockMvcResultMatchers.status().isOk())
                .andExpect(MockMvcResultMatchers.content().string("Disciplina deletada com sucesso"));


    }


}

