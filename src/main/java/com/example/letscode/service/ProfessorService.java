package com.example.letscode.service;

import com.example.letscode.exception.ProfessorJaExisteException;
import com.example.letscode.exception.ProfessorNaoEncontradoException;
import com.example.letscode.model.Professor;
import com.example.letscode.repository.ProfessorRepository;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.stereotype.Service;

import java.util.List;

@Service
public class ProfessorService {

    private final ProfessorRepository professorRepository;
    private final Logger LOGGER = LoggerFactory.getLogger(ProfessorService.class);

    public ProfessorService(ProfessorRepository professorRepository) {
        this.professorRepository = professorRepository;
    }

    public Professor selecionarProfessor(Integer id){
        LOGGER.info("Selecionando Professor pelo ID ", id);
        return this.professorRepository.findById(id).orElseThrow(ProfessorNaoEncontradoException::new);
    }

    public List<Professor> listarTodos(){
        return this.professorRepository.findAll();
    }

    public Professor salvarProfessor(Professor professor){
        LOGGER.info("Início do método salvar - Professor");
        if(!this.professorRepository.existsByNome(professor.getNome())){
            this.professorRepository.save(professor);
        }else{
            throw new ProfessorJaExisteException();
        }
        LOGGER.info("Professor salvo com sucesso");
        return professor;
    }

    public Professor alterarProfessor(Integer id, Professor professorRequest){
        LOGGER.info("Início do método alterar - Professor");
        Professor professor = this.selecionarProfessor(id);
        professor.setNome(professorRequest.getNome());
        this.salvarProfessor(professor);
        LOGGER.info("Professor alterado com sucesso");
        return professor;
    }

    public Professor deletarProfessor(Integer id){
        LOGGER.info("Início do método deletar - Professor");
        Professor professor = selecionarProfessor(id);
        this.professorRepository.delete(professor);
        LOGGER.info("Professor deletado com sucesso");
        return professor;
    }

    public Professor selecionarProfessorById(Integer id) {
        LOGGER.info("Selecionando Professor pelo ID {}", id);
        return this.professorRepository.findById(id).orElseThrow(ProfessorNaoEncontradoException::new);
    }
}