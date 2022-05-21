package com.example.letscode.testesUnitariosController;

import com.example.letscode.controller.AlunoController;
import com.example.letscode.dto.AlunoDto;
import com.example.letscode.model.Aluno;
import com.example.letscode.service.AlunoService;
import org.junit.jupiter.api.BeforeEach;
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

import java.time.LocalDate;
import java.util.ArrayList;
import java.util.List;

@ContextConfiguration
@ExtendWith(SpringExtension.class)
@WebMvcTest(value = AlunoController.class)
class AlunoControllerTest {

    @MockBean
    private AlunoService alunoService;

    @Autowired
    private MockMvc mockMvc;

    private AlunoDto alunoDto;
    private Aluno aluno;
    private Aluno alunoAddList;
    private Aluno alunoAddList2;
    private List<Aluno> alunoList;

    @BeforeEach
    public void inicializar(){
        alunoDto = new AlunoDto(1, "Alisson", "MTLA122395", LocalDate.now());
        aluno = new Aluno();
        aluno.setId(alunoDto.getId());
        aluno.setNome(alunoDto.getNome());
        aluno.setDataNascimento(alunoDto.getDataNascimento());
        aluno.setMatricula(alunoDto.getMatricula());

        alunoAddList = new Aluno("Amora", "MTLA120385", LocalDate.now());
        alunoAddList2 = new Aluno("Amanda", "MTLA220365", LocalDate.now());
        alunoList = new ArrayList<>();
        alunoList.add(alunoAddList);
        alunoList.add(alunoAddList2);

    }

    @Test
    @WithMockUser
    void listarTodosAlunos() throws Exception {

        Mockito.when(alunoService.listarTodos()).thenReturn(alunoList);
        this.mockMvc.perform(
                        MockMvcRequestBuilders.get("/aluno")
                )
                .andDo(MockMvcResultHandlers.print())
                .andExpect(MockMvcResultMatchers.status().isOk());

    }

    @Test
    @WithMockUser
    void salvarAlunoTestController() throws Exception {

        Mockito.when(alunoService.salvarAluno(aluno))
                .thenReturn(aluno);

        this.mockMvc.perform(
                MockMvcRequestBuilders.post("/aluno/salva")
                        .flashAttr("aluno", aluno)
        ).andDo(MockMvcResultHandlers.print());
    }

    @Test
    @WithMockUser
    void deletarAlunoTestController() throws Exception {

        Mockito.when(alunoService.deletarAluno(aluno.getId())).thenReturn(aluno);

        this.mockMvc.perform(
                        MockMvcRequestBuilders
                                .delete("/aluno/1")
                                .accept(MediaType.APPLICATION_JSON).accept(MediaType.APPLICATION_JSON)

                )
                .andDo(MockMvcResultHandlers.print())
                .andExpect(MockMvcResultMatchers.status().isOk())
                .andExpect(MockMvcResultMatchers.content().string("Aluno deletado com sucesso"));


    }

    @Test
    @WithMockUser
    void buscarAlunoTestController() throws Exception {

        Mockito.when(alunoService.selecionarAlunoById(aluno.getId())).thenReturn(aluno);
        this.mockMvc.perform(
                        MockMvcRequestBuilders
                                .get("/aluno/1")
                                .accept(MediaType.APPLICATION_JSON).accept(MediaType.APPLICATION_JSON)

                )
                .andDo(MockMvcResultHandlers.print())
                .andExpect(MockMvcResultMatchers.status().isOk());

    }
}