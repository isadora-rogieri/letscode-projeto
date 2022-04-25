package com.example.letscode.service;

import com.example.letscode.exception.DisciplinaJaExisteException;
import com.example.letscode.exception.DisciplinaNaoEncontradaException;
import com.example.letscode.model.Disciplina;
import com.example.letscode.repository.DisciplinaRepository;
import org.springframework.stereotype.Service;

import java.util.List;

@Service
public class DisciplinaService {

    private final DisciplinaRepository disciplinaRepository;


    public DisciplinaService(DisciplinaRepository disciplinaRepository) {
        this.disciplinaRepository = disciplinaRepository;
    }

    public Disciplina selecionarDisciplina(Integer id){
        return this.disciplinaRepository.findById(id).orElseThrow(DisciplinaNaoEncontradaException::new);

    }

    public void salvarDisciplina(Disciplina disciplina) {
        if(!this.disciplinaRepository.existsByNome(disciplina.getNome())){
            this.disciplinaRepository.save(disciplina);
        }else{
            throw new DisciplinaJaExisteException();
        }
    }

    public Disciplina alterarDisciplina(Integer id, Disciplina disciplinaRequest){
        Disciplina disciplina = this.selecionarDisciplina(id);
        disciplina.setNome(disciplinaRequest.getNome());
        this.salvarDisciplina(disciplina);
        return disciplina;
    }

    public Disciplina deletarDisciplina(Integer id){
        Disciplina disciplina = selecionarDisciplina(id);
        this.disciplinaRepository.delete(disciplina);
        return disciplina;
    }

    public List<Disciplina> listarTodos() {
        return this.disciplinaRepository.findAll();
    }

    public Disciplina selecionarDisciplinaById(Integer id) {
        return this.disciplinaRepository.findById(id).orElseThrow(DisciplinaNaoEncontradaException::new);
    }
}