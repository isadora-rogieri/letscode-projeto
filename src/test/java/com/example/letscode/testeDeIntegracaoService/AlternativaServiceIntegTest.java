package com.example.letscode.testeDeIntegracaoService;

import com.example.letscode.model.Alternativa;
import com.example.letscode.model.Disciplina;
import com.example.letscode.model.Professor;
import com.example.letscode.model.Questao;
import com.example.letscode.service.AlternativaService;
import org.junit.jupiter.api.BeforeAll;
import org.junit.jupiter.api.Test;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;

import javax.transaction.Transactional;
import java.util.ArrayList;
import java.util.List;
import java.util.concurrent.atomic.AtomicReference;

import static org.junit.jupiter.api.Assertions.*;

@SpringBootTest(webEnvironment = SpringBootTest.WebEnvironment.RANDOM_PORT)
class AlternativaServiceIntegTest {

    @Autowired
    AlternativaService alternativaService;

    private static List<Alternativa> listaAlternativas = new ArrayList<>();

    @BeforeAll
    private static void beforeAll() {
        Professor professor = new Professor("Professor");
        Disciplina disciplina = new Disciplina("disciplina",professor);
        Questao questao1 = new Questao(1,"questao 1", disciplina);
        listaAlternativas.add(new Alternativa( 100,"Alternativa 1", true, questao1));
        listaAlternativas.add(new Alternativa( 2,"Alternativa 2", false, questao1));
        listaAlternativas.add(new Alternativa( 3,"Alternativa 3", false, questao1));
        listaAlternativas.add(new Alternativa( 4,"Alternativa 4", false, new Questao(2,"questao 2", disciplina)));
        listaAlternativas.add(new Alternativa( 5,"Alternativa 5", false, new Questao(3,"questao 3", disciplina)));
    }

    @Test
    @Transactional
    void deveRetornarAlternativaSalva() {
        Alternativa alternativaSalva = alternativaService.salvarAlternativa(listaAlternativas.get(0));

        assertNotNull(alternativaSalva.getId());
        assertEquals(listaAlternativas.get(0).getDescricao(), alternativaSalva.getDescricao());
        assertEquals(listaAlternativas.get(0).getEhResposta(), alternativaSalva.getEhResposta());
        assertEquals(listaAlternativas.get(0).getQuestao().getId(), alternativaSalva.getQuestao().getId());
    }

    @Test
    void deveRetornarExceptionQuandoalternativaJaExiste() {
        try {
            Alternativa alternativaSalva = alternativaService.salvarAlternativa(listaAlternativas.get(1));
            fail("Deveria dar erro");
        } catch (Exception e) {
            assertEquals("Alternativa já existe", e.getMessage());
        }
    }

    @Test
    void deveRetornarListaDeAlternativas() {

        List<Alternativa> alternativas = alternativaService.listarAlternativas();

        assertNotNull(alternativas);
        assertTrue(alternativas.size() > 0);

    }

    @Test
    @Transactional
    void deveRetornarListaComAAlternativaSalva() {

        Alternativa alternativaSalva = alternativaService.salvarAlternativa(listaAlternativas.get(0));

        List<Alternativa> alternativasLista = alternativaService.listarAlternativas();

        assertTrue(alternativasLista.contains(alternativaSalva));

        AtomicReference<Alternativa> alternativaSalvaLista = new AtomicReference<>();
        alternativasLista.forEach(alternativa -> {
            if (alternativa.equals(alternativaSalva)) {
                alternativaSalvaLista.set(alternativa);
            }
        });

        assertNotNull(alternativaSalvaLista.get());
        assertEquals(alternativaSalva.getDescricao(), alternativaSalvaLista.get().getDescricao());
        assertEquals(alternativaSalva.getEhResposta(), alternativaSalvaLista.get().getEhResposta());
        assertEquals(alternativaSalva.getQuestao(), alternativaSalvaLista.get().getQuestao());

    }

}
