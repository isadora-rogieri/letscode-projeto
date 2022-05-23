package com.example.letscode.TestesUnitarios;

import com.example.letscode.dto.AlternativaDto;
import com.example.letscode.dto.DisciplinaDto;
import com.example.letscode.dto.DtoChange;
import com.example.letscode.dto.QuestaoDto;
import com.example.letscode.model.Alternativa;
import com.example.letscode.model.Disciplina;
import com.example.letscode.model.Professor;
import com.example.letscode.model.Questao;
import com.example.letscode.service.DisciplinaService;
import com.example.letscode.service.ProfessorService;
import com.example.letscode.service.QuestaoService;
import org.junit.jupiter.api.Assertions;
import org.junit.jupiter.api.Test;
import org.junit.jupiter.api.extension.ExtendWith;
import org.mockito.InjectMocks;
import org.mockito.Mock;
import org.mockito.Mockito;
import org.mockito.junit.jupiter.MockitoExtension;

import static org.assertj.core.api.Assertions.assertThat;

@ExtendWith(MockitoExtension.class)
public class DtoChangeTest {

    @InjectMocks
    private DtoChange dtoChange;
    @Mock
    private ProfessorService professorService;
    @Mock
    private QuestaoService questaoService;
    @Mock
    private DisciplinaService disciplinaService;

    @Test
    public void deveRetornarAlternativaDto() {
        Alternativa alternativa = new Alternativa( 5,"Alternativa 5", false,
                new Questao(3,"questao 3", new Disciplina("teste", new Professor("José"))));

        AlternativaDto alternativaDto = dtoChange.alternativaToAlternativaDto(alternativa);

        assertThat(alternativaDto)
                .hasFieldOrPropertyWithValue("id", 5)
                .hasFieldOrPropertyWithValue("descricao", "Alternativa 5")
                .hasFieldOrPropertyWithValue("ehResposta", false)
                .hasFieldOrPropertyWithValue("questao_id", 3);
    }

    @Test
    public void deveRetornarAlternativa() {
        AlternativaDto alternativaDto = new AlternativaDto(5, "Alternativa 5", false, 3);
        Questao questao = new Questao(3, "questao 3", new Disciplina("teste", new Professor("José")));


        Mockito.when(questaoService.buscarQuestaoporId(3))
                .thenReturn(questao);
        Alternativa alternativa = dtoChange.alternativaDtoToAlternativa(alternativaDto);

        assertThat(alternativa)
                .hasFieldOrPropertyWithValue("id", 5)
                .hasFieldOrPropertyWithValue("descricao", "Alternativa 5")
                .hasFieldOrPropertyWithValue("ehResposta", false);
        Assertions.assertEquals(questao, alternativa.getQuestao());

    }

    @Test
    public void deveRetornarQuestaoDto() {
        Questao questao = new Questao( 1,"Questao 1", new Disciplina(1, "disciplina 1", new Professor("João")));
        QuestaoDto questaoDto = dtoChange.questaoToQuestaoDto(questao);

        assertThat(questaoDto)
                .hasFieldOrPropertyWithValue("id", 1)
                .hasFieldOrPropertyWithValue("enunciado", "Questao 1")
                .hasFieldOrPropertyWithValue("disciplina_id", 1);
    }

    @Test
    public void deveRetornarQuestao() {
        QuestaoDto questaoDto = new QuestaoDto(1, "Questao 1", 1);
        Disciplina disciplina = new Disciplina(1, "disciplina 1", new Professor("João"));

        Mockito.when(disciplinaService.selecionarDisciplinaById(1))
                .thenReturn(disciplina);
        Questao questao = dtoChange.questaoDtoToQuestao(questaoDto);

        assertThat(questao)
                .hasFieldOrPropertyWithValue("id", 1)
                .hasFieldOrPropertyWithValue("enunciado", "Questao 1");
        Assertions.assertEquals(disciplina, questao.getDisciplina());
    }

    @Test
    public void deveRetornarDisciplinaDto() {
        Disciplina disciplina = new Disciplina(1, "disciplina 1", new Professor( 2, "João"));
        DisciplinaDto disciplinaDto = dtoChange.disciplinaToDisciplinaDto(disciplina);

        assertThat(disciplinaDto)
                .hasFieldOrPropertyWithValue("id", 1)
                .hasFieldOrPropertyWithValue("nome", "disciplina 1")
                .hasFieldOrPropertyWithValue("professor_id", 2);
    }

    @Test
    public void deveRetornarDisciplina() {
        DisciplinaDto disciplinaDto = new DisciplinaDto(1, "disciplina 1", 2);
        Professor professor = new Professor(2,  "João");

        Mockito.when(professorService.selecionarProfessor(2))
                .thenReturn(professor);
        Disciplina disciplina = dtoChange.disciplinaDtoToDisciplina(disciplinaDto);

        assertThat(disciplina)
                .hasFieldOrPropertyWithValue("id", 1)
                .hasFieldOrPropertyWithValue("nome", "disciplina 1");
        Assertions.assertEquals(professor, disciplina.getProfessor());
    }
}