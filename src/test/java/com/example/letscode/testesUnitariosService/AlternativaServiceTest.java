package com.example.letscode.testesUnitariosService;

import com.example.letscode.exception.AlternativaJaExisteException;
import com.example.letscode.model.Alternativa;
import com.example.letscode.model.Disciplina;
import com.example.letscode.model.Professor;
import com.example.letscode.model.Questao;
import com.example.letscode.repository.AlternativaRepository;
import com.example.letscode.service.AlternativaService;
import org.junit.jupiter.api.Assertions;
import org.junit.jupiter.api.BeforeAll;
import org.junit.jupiter.api.Test;
import org.junit.jupiter.api.extension.ExtendWith;
import org.mockito.InjectMocks;
import org.mockito.Mock;
import org.mockito.Mockito;
import org.mockito.junit.jupiter.MockitoExtension;

import java.util.ArrayList;
import java.util.List;
import java.util.Optional;
import java.util.stream.Collectors;

import static org.junit.jupiter.api.Assertions.assertEquals;
import static org.mockito.Mockito.verify;

@ExtendWith(MockitoExtension.class)
class AlternativaServiceTest {

    @InjectMocks
    private AlternativaService alternativaService;

    @Mock
    private AlternativaRepository alternativaRepository;

    private static List<Alternativa> listaAlternativas = new ArrayList<>();

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
    }


    @Test
    void deveriaRetornarListadeTodasAlternativas() {
        Mockito.when(alternativaRepository.findAll())
                .thenReturn(listaAlternativas);

        List<Alternativa> alternativas = alternativaService.listarAlternativas();

        Assertions.assertNotNull(alternativas);
        Assertions.assertFalse(alternativas.isEmpty());
        assertEquals(5, listaAlternativas.size());
    }

    @Test
    void deveriaRetornarAlternativaComId() {

        Mockito.when(alternativaRepository.findById(1))
                .thenReturn(Optional.of(listaAlternativas.get(0)));

        Alternativa alternativa = alternativaService.buscarAlternativaPorId(1);

        Assertions.assertNotNull(alternativa);
        assertEquals(listaAlternativas.get(0), alternativa);
        assertEquals(1, alternativa.getId());

    }

    @Test
    void deveriaRetornarExceptionQuandoAlternativaNaoEncontrada() {
        try {
            alternativaService.buscarAlternativaPorId(7);
        } catch (Exception e) {
            assertEquals("Alternativa não encontrada", e.getMessage());
        }
    }

    @Test
    void deveriaRetornarAlternativasComQuestaoId() {

        List<Alternativa> alternativas = listaAlternativas.stream()
                .filter(a -> a.getQuestao().getId().equals(1))
                .collect(Collectors.toList());

        Mockito.when(alternativaRepository.findAllByQuestaoId(1))
                .thenReturn(alternativas);

        List<Alternativa> alternativaQuestao = alternativaService.buscarAlternativasPorQuestaoId(1);

        Assertions.assertNotNull(alternativaQuestao);
        Assertions.assertFalse(alternativas.isEmpty());
        assertEquals(3, alternativaQuestao.size());
        alternativaQuestao.forEach(x -> assertEquals(1, x.getQuestao().getId()));

    }

    @Test
    void deveRetornarAlternativaSalva() {

        Alternativa alternativaSalvar = listaAlternativas.get(0);

        Mockito.when(alternativaRepository.save(alternativaSalvar))
                .thenReturn(listaAlternativas.get(0));

        Alternativa alternativaRetorno = alternativaService.salvarAlternativa(alternativaSalvar);

        Assertions.assertNotNull(alternativaRetorno);
        Assertions.assertNotNull(alternativaRetorno.getId());
        Assertions.assertEquals(1, alternativaRetorno.getId());
        Assertions.assertEquals("Alternativa 1", alternativaRetorno.getDescricao());
        Assertions.assertEquals(true, alternativaRetorno.getEhResposta());
        Assertions.assertEquals(listaAlternativas.get(0).getQuestao(), alternativaRetorno.getQuestao());
    }

    @Test
    void deveRetornarExceptionQuandoDescricaoJaExistePorQuestao() {

        Alternativa alternativaSalvar = listaAlternativas.get(0);

        List<Alternativa> alternativasQuestao = listaAlternativas.stream()
                .filter(a -> a.getQuestao().getId().equals(alternativaSalvar.getId()))
                .collect(Collectors.toList());

        Mockito.when(alternativaRepository.findAllByQuestaoId(alternativaSalvar.getId()))
                .thenReturn(alternativasQuestao);

        Exception ex = Assertions.assertThrows(AlternativaJaExisteException.class, () -> {
            alternativaService.salvarAlternativa(alternativaSalvar);
        });
        Assertions.assertEquals("Alternativa já existe", ex.getMessage());
    }

    @Test
    void deveRetornarExceptionQuandoAlternativaJaExisteComId() {

        Alternativa alternativaSalvar = listaAlternativas.get(0);

        Mockito.when(alternativaRepository.existsById(alternativaSalvar.getId()))
                .thenReturn(true);

        Exception ex = Assertions.assertThrows(AlternativaJaExisteException.class, () -> {
            alternativaService.salvarAlternativa(alternativaSalvar);
        });
        Assertions.assertEquals("Alternativa já existe", ex.getMessage());
    }

    @Test
    void deveRetornarAlternativaAtualizada() {

        Questao questao = new Questao(4,"questao teste", new Disciplina("disciplina teste", new Professor("prof")));
        Alternativa alternativaAtualizada = new Alternativa( 5,"Alternativa atualizada",
                false, questao);

        Mockito.when(alternativaRepository.findById(5))
                .thenReturn(Optional.of(listaAlternativas.get(4)));

        Alternativa alternativaSalva = listaAlternativas.get(4);
        alternativaSalva.setDescricao(alternativaAtualizada.getDescricao());
        alternativaSalva.setEhResposta(alternativaAtualizada.getEhResposta());
        alternativaSalva.setQuestao(alternativaAtualizada.getQuestao());

        Mockito.when(alternativaRepository.save(alternativaSalva))
                .thenReturn(alternativaAtualizada);

        Alternativa alternativaRetorno = alternativaService.atualizarAlternativa(5, alternativaAtualizada);

        Assertions.assertNotNull(alternativaRetorno);
        assertEquals(5, alternativaRetorno.getId());
        assertEquals("Alternativa atualizada", alternativaRetorno.getDescricao());
        assertEquals(false, alternativaRetorno.getEhResposta());
        assertEquals(questao, alternativaRetorno.getQuestao());
    }


    @Test
    void deveChamarMetodoDeletarPassandoAlternativa() {

        Mockito.when(alternativaRepository.findById(1))
                .thenReturn(Optional.of(listaAlternativas.get(0)));

        alternativaService.deletarAlternativa(1);

        verify(alternativaRepository).delete(listaAlternativas.get(0));

    }


}
