package com.example.letscode.TestesUnitariosController;

import com.example.letscode.controller.AlunoController;
import com.example.letscode.model.Aluno;
import com.example.letscode.service.AlunoService;
import org.junit.jupiter.api.Test;
import org.junit.jupiter.api.extension.ExtendWith;
import org.mockito.Mockito;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.autoconfigure.web.servlet.WebMvcTest;
import org.springframework.boot.test.mock.mockito.MockBean;
import org.springframework.security.test.context.support.WithMockUser;
import org.springframework.test.context.ContextConfiguration;
import org.springframework.test.context.junit.jupiter.SpringExtension;
import org.springframework.test.web.servlet.MockMvc;
import org.springframework.test.web.servlet.request.MockMvcRequestBuilders;
import org.springframework.test.web.servlet.result.MockMvcResultHandlers;

import java.time.LocalDate;
import java.util.ArrayList;
import java.util.List;

@ContextConfiguration
@ExtendWith(SpringExtension.class)
@WebMvcTest(value = AlunoController.class)
public class AlunoControllerTest{

    @MockBean
    private AlunoService alunoService;

    @Autowired
    private MockMvc mockMvc;

    @Test
    @WithMockUser
    void listarTdososAlunos() throws Exception{

        List<Aluno> alunoList = new ArrayList<>();
        alunoList.add(new Aluno("Alan", "MTLA125478", LocalDate.now()));
        alunoList.add(new Aluno("Mateus", "MTLA125471", LocalDate.now()));
        alunoList.add(new Aluno("João", "MTLA125479", LocalDate.now()));

        Mockito.when(alunoService.listarTodos()).thenReturn(alunoList);
        this.mockMvc.perform(
                        MockMvcRequestBuilders.get("/aluno")
                )
                .andDo(MockMvcResultHandlers.print());
    }
}
