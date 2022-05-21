package com.example.letscode.testesUnitariosController;

import com.example.letscode.controller.QuestaoController;
import com.example.letscode.service.QuestaoService;
import org.junit.jupiter.api.extension.ExtendWith;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.autoconfigure.web.servlet.WebMvcTest;
import org.springframework.boot.test.mock.mockito.MockBean;
import org.springframework.test.context.ContextConfiguration;
import org.springframework.test.context.junit.jupiter.SpringExtension;
import org.springframework.test.web.servlet.MockMvc;

@ContextConfiguration
@ExtendWith(SpringExtension.class)
@WebMvcTest(value = QuestaoController.class)
public class QuestaoControllerTest {

    @MockBean
    private QuestaoService questaoService;

    @Autowired
    private MockMvc mockMvc;

}
