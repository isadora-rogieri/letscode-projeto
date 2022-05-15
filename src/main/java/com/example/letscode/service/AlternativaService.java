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
    private static final Logger LOGGER = LoggerFactory.getLogger(AlternativaService.class);
    
    private static final String ALTERNATIVA = "Alternativa";

    public AlternativaService(AlternativaRepository alternativaRepository) {
        this.alternativaRepository = alternativaRepository;
    }

    public List<Alternativa> listarAlternativas() {
        return this.alternativaRepository.findAll();
    }


    public List<Alternativa> buscarAlternativasPorQuestaoId(Integer questaoId) {
        LOGGER.info("Buscando alternativa por questão {} ", questaoId);
        return this.alternativaRepository.findAllByQuestaoId(questaoId);
    }

    public Alternativa buscarAlternativaPorId(Integer id) {
        LOGGER.info("Buscando alternativa pelo ID {}", id);
        return this.alternativaRepository.findById(id).orElseThrow(AlternativaNaoEncontradaException::new);
    }

    public Alternativa salvarAlternativa(Alternativa alternativa) {
        List<Alternativa> list = this.buscarAlternativasPorQuestaoId(alternativa.getQuestao().getId());
        List<Alternativa> lista = list.stream()
                .filter(x -> x.getDescricao().equals(alternativa.getDescricao()))
                .collect(Collectors.toList());

        if(lista.isEmpty() && !(this.alternativaRepository.existsById(alternativa.getId()))) {
            var alternativaSalva = this.alternativaRepository.save(alternativa);
            LOGGER.info("{} {} salva com sucesso", ALTERNATIVA, alternativaSalva.getId());
            return alternativaSalva;
        } else {
            throw new AlternativaJaExisteException();
        }
    }

    public Alternativa atualizarAlternativa(Integer id, Alternativa alternativa) {
        var alternativaBd = this.buscarAlternativaPorId(id);
        alternativaBd.setDescricao(alternativa.getDescricao());
        alternativaBd.setEhResposta(alternativa.getEhResposta());
        alternativaBd.setQuestao(alternativa.getQuestao());

        var alternativaSalva = this.alternativaRepository.save(alternativaBd);
        LOGGER.info("{} {} atualizada com sucesso", ALTERNATIVA, alternativaSalva.getId());
        return alternativaSalva;
    }

    public void deletarAlternativa(Integer id) {
        var alternativaBd = this.buscarAlternativaPorId(id);
        this.alternativaRepository.delete(alternativaBd);
        LOGGER.info("{} {} deletada com sucesso", ALTERNATIVA, id);
    }
}
