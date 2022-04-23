package com.example.letscode.service;

import com.example.letscode.exception.ProfessorNaoEncontradoException;
import com.example.letscode.model.professor.Professor;
import com.example.letscode.repository.ProfessorRepository;
import org.springframework.stereotype.Service;

import java.util.List;

@Service
public class ProfessorService {

    private final ProfessorRepository professorRepository;

    public ProfessorService(ProfessorRepository professorRepository) {
        this.professorRepository = professorRepository;
    }

    public Professor selecionarProfessor(Integer id){
        return this.professorRepository.findById(id).orElseThrow(ProfessorNaoEncontradoException::new);
    }
    public List<Professor> listarTodos(){
        return this.professorRepository.findAll();
    }
    public void salvarProfessor(Professor professor){
        this.professorRepository.save(professor);
    }
    public void alterarProfessor(Integer id, Professor professorRequest){
        Professor professor = this.selecionarProfessor(id);
        professor.setNome(professorRequest.getNome());
        this.salvarProfessor(professor);
    }
    public void deletarProfessor(Integer id){
        Professor professor = selecionarProfessor(id);
        this.professorRepository.delete(professor);
        }



}
