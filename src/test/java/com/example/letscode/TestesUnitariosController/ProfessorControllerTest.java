package com.example.letscode.TestesUnitariosController;

import com.example.letscode.controller.ProfessorController;
import com.example.letscode.model.Professor;
import com.example.letscode.service.ProfessorService;
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

@ContextConfiguration
@ExtendWith(SpringExtension.class)
@WebMvcTest(value = ProfessorController.class)
class ProfessorControllerTest {

    @MockBean
    private ProfessorService professorService;

    @Autowired
    private MockMvc mockMvc;

    @Test
    @WithMockUser
    void listarTodosProfessores() throws Exception {

        List<Professor> professorList = new ArrayList<>();
        professorList.add(new Professor("Rodrigo"));
        professorList.add(new Professor("Jessé"));
        professorList.add(new Professor("Haron"));

        Mockito.when(professorService.listarTodos()).thenReturn(professorList);
        this.mockMvc.perform(
                        MockMvcRequestBuilders.get("/professor")
                )
                .andDo(MockMvcResultHandlers.print())
                .andExpect(MockMvcResultMatchers.status().isOk());

    }
    @Test
    @WithMockUser
    void buscarProfessor() throws Exception {


        Professor professor= new Professor("Rodrigo");
        professor.setId(1);

        Mockito.when(professorService.selecionarProfessorById(professor.getId())).thenReturn(professor);
        this.mockMvc.perform(
                        MockMvcRequestBuilders
                                .get("/professor/1")
                                .accept(MediaType.APPLICATION_JSON).accept(MediaType.APPLICATION_JSON)

                )
                .andDo(MockMvcResultHandlers.print())
                .andExpect(MockMvcResultMatchers.status().isOk());

    }
    @Test
    @WithMockUser
    void deletarProfessor() throws Exception {


        Professor professor= new Professor("Rodrigo");
        professor.setId(1);

        Mockito.when(professorService.deletarProfessor(professor.getId())).thenReturn(professor);

        this.mockMvc.perform(
                        MockMvcRequestBuilders
                                .delete("/professor/1")
                                .accept(MediaType.APPLICATION_JSON).accept(MediaType.APPLICATION_JSON)

                )
                .andDo(MockMvcResultHandlers.print())
                .andExpect(MockMvcResultMatchers.status().isOk())
                .andExpect(MockMvcResultMatchers.content().string("Professor deletado com sucesso"));


    }

    @Test
    @WithMockUser
    void salvarProfessorTestController() throws Exception{

        Professor professor = new Professor("Rodrigo");


        Mockito.when(professorService.salvarProfessor(professor))
                .thenReturn(professor);

        this.mockMvc.perform(
                MockMvcRequestBuilders.post("/professor/salva")
                        .flashAttr("professor", professor)
        ).andDo(MockMvcResultHandlers.print());
    }
}
