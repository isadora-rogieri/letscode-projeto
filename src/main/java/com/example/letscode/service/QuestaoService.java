package com.example.letscode.service;

import com.example.letscode.exception.QuestaoJaExisteException;
import com.example.letscode.exception.QuestaoNaoEncontradaException;
import com.example.letscode.model.Questao;
import com.example.letscode.repository.QuestaoRepository;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.stereotype.Service;

import java.util.List;

@Service
public class QuestaoService {

    private static final Logger LOGGER = LoggerFactory.getLogger(QuestaoService.class);
    private final QuestaoRepository questaoRepository;

    public QuestaoService(QuestaoRepository questaoRepository) {
        this.questaoRepository = questaoRepository;
    }

    public List<Questao> listarQuestoes() {
        return this.questaoRepository.findAll();
    }

    public Questao buscarQuestaoporId(Integer id) {
        LOGGER.info("Selecionando Questão pelo ID ", id);
        return this.questaoRepository.findById(id).orElseThrow(QuestaoNaoEncontradaException::new);
    }

    public Questao salvarQuestao(Questao questao) {
        LOGGER.info("Início do método salvar - Questão");
        if(!(this.questaoRepository.existsByEnunciado(questao.getEnunciado())
            || this.questaoRepository.existsById(questao.getId()))){
            Questao questaoSalva = this.questaoRepository.save(questao);
            LOGGER.info("Questao " + questaoSalva.getId() + " salva com sucesso");
            return questaoSalva;
        }else{
            throw new QuestaoJaExisteException();
        }
    }

    public Questao atualizarQuestao(Integer id, Questao questaoAtualizada) {
        LOGGER.info("Início do método atualizar - Questão");
        Questao questao = this.buscarQuestaoporId(id);
        questao.setEnunciado(questaoAtualizada.getEnunciado());
        questao.setDisciplina(questaoAtualizada.getDisciplina());

        Questao questaoSalva = this.questaoRepository.save(questao);
        LOGGER.info("Questao " + questaoSalva.getId() + " atualizada com sucesso");
        return questaoSalva;
    }

    public void deletarQuestao(Integer id) {
        LOGGER.info("Início do método deletar - Questão");
        Questao questao = this.buscarQuestaoporId(id);
        this.questaoRepository.delete(questao);
        LOGGER.info("Questao " + id + " deletada com sucesso");
    }

}
