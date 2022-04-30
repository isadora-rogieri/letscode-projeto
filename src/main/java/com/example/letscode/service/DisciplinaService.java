package com.example.letscode.service;

import com.example.letscode.exception.DisciplinaJaExisteException;
import com.example.letscode.exception.DisciplinaNaoEncontradaException;
import com.example.letscode.exception.ProfessorNaoEncontradoException;
import com.example.letscode.model.Disciplina;
import com.example.letscode.model.Professor;
import com.example.letscode.repository.DisciplinaRepository;
import com.example.letscode.repository.ProfessorRepository;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.stereotype.Service;

import javax.validation.Valid;
import java.util.List;

@Service
public class DisciplinaService {

    private final DisciplinaRepository disciplinaRepository;

    private final Logger LOGGER = LoggerFactory.getLogger(DisciplinaService.class);


    public DisciplinaService(DisciplinaRepository disciplinaRepository) {
        this.disciplinaRepository = disciplinaRepository;

    }

    public Disciplina selecionarDisciplina(Integer id){
        return this.disciplinaRepository.findById(id).orElseThrow(DisciplinaNaoEncontradaException::new);

    }

    public void salvarDisciplina(Disciplina disciplina) {
        LOGGER.info("Início do método salvar - Disciplina");
        if(!this.disciplinaRepository.existsByNome(disciplina.getNome())   &&
         this.disciplinaRepository.existsByProfessorId(disciplina.getProfessor().getId())){

            this.disciplinaRepository.save(disciplina);

        }else if(this.disciplinaRepository.existsByNome(disciplina.getNome())){
            throw new DisciplinaJaExisteException();
        }else{
            throw new ProfessorNaoEncontradoException();
        }
        LOGGER.info("Disciplina salva com sucesso");
    }

    public Disciplina alterarDisciplina(Integer id, Disciplina disciplinaRequest){
        LOGGER.info("Início do método alterar  - Disciplina");
        Disciplina disciplina = this.selecionarDisciplina(id);
        disciplina.setNome(disciplinaRequest.getNome());
        this.salvarDisciplina(disciplina);
        LOGGER.info("Disciplina alterada com sucesso");
        return disciplina;

    }

    public Disciplina deletarDisciplina(Integer id){
        LOGGER.info("Início do método deletar - Disciplina");
        Disciplina disciplina = selecionarDisciplina(id);
        this.disciplinaRepository.delete(disciplina);
        LOGGER.info("Disciplina deletada com sucesso");
        return disciplina;
    }

    public List<Disciplina> listarTodos() {
        return this.disciplinaRepository.findAll();
    }

    public Disciplina selecionarDisciplinaById(Integer id) {
        return this.disciplinaRepository.findById(id).orElseThrow(DisciplinaNaoEncontradaException::new);
    }
}