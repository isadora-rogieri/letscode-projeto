package com.example.letscode.service;

import com.example.letscode.exception.AlternativaJaExisteException;
import com.example.letscode.exception.AlternativaNaoEncontradaException;
import com.example.letscode.model.Alternativa;
import com.example.letscode.repository.AlternativaRepository;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.stereotype.Service;

import java.util.List;
import java.util.stream.Collectors;

@Service
public class AlternativaService {

    private final AlternativaRepository alternativaRepository;
    private static final Logger LOGGER = LoggerFactory.getLogger(QuestaoService.class);

    public AlternativaService(AlternativaRepository alternativaRepository) {
        this.alternativaRepository = alternativaRepository;
    }

    public List<Alternativa> listarAlternativas() {
        return this.alternativaRepository.findAll();
    }


    public List<Alternativa> buscarAlternativasPorQuestaoId(Integer questaoId) {
        return this.alternativaRepository.findAllByQuestaoId(questaoId);
    }

    public Alternativa buscarAlternativaPorId(Integer id) {
        return this.alternativaRepository.findById(id).orElseThrow(AlternativaNaoEncontradaException::new);
    }

    public Alternativa salvarAlternativa(Alternativa alternativa) {
        List<Alternativa> list = this.buscarAlternativasPorQuestaoId(alternativa.getQuestao().getId());
        List<Alternativa> lista = list.stream()
                .filter(x -> x.getDescricao().equals(alternativa.getDescricao()))
                .collect(Collectors.toList());

        if(lista.isEmpty() && !(this.alternativaRepository.existsById(alternativa.getId()))) {
            Alternativa alternativaSalva = this.alternativaRepository.save(alternativa);
            LOGGER.info("Alternativa " + alternativaSalva.getId() + " salva com sucesso");
            return alternativaSalva;
        } else {
            throw new AlternativaJaExisteException();
        }
    }

    public Alternativa atualizarAlternativa(Integer id, Alternativa alternativa) {
        Alternativa alternativaBd = this.buscarAlternativaPorId(id);
        alternativaBd.setDescricao(alternativa.getDescricao());
        alternativaBd.setEhResposta(alternativa.getEhResposta());
        alternativaBd.setQuestao(alternativa.getQuestao());

        Alternativa alternativaSalva = this.alternativaRepository.save(alternativaBd);
        LOGGER.info("Alternativa " + alternativaSalva.getId() + " atualizada com sucesso");
        return alternativaSalva;
    }

    public void deletarAlternativa(Integer id) {
        Alternativa alternativaBd = this.buscarAlternativaPorId(id);
        this.alternativaRepository.delete(alternativaBd);
        LOGGER.info("Alternativa " + alternativaBd.getId() + " deletada com sucesso");
    }
}
