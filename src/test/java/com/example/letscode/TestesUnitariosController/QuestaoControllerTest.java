package com.example.letscode.TestesUnitariosController;

import com.example.letscode.controller.QuestaoController;
import com.example.letscode.model.Disciplina;
import com.example.letscode.model.Professor;
import com.example.letscode.model.Questao;
import com.example.letscode.service.ProfessorService;
import com.example.letscode.service.QuestaoService;
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
import org.springframework.test.web.servlet.result.MockMvcResultMatchers;

import java.util.ArrayList;
import java.util.List;

@ContextConfiguration
@ExtendWith(SpringExtension.class)
@WebMvcTest(value = QuestaoController.class)
public class QuestaoControllerTest {

    @MockBean
    private QuestaoService questaoService;

    @Autowired
    private MockMvc mockMvc;

}
