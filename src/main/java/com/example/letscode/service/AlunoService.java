package com.example.letscode.service;

import com.example.letscode.exception.AlunoNaoEncontradoException;
import com.example.letscode.model.aluno.Aluno;
import com.example.letscode.repository.AlunoRepository;
import org.springframework.stereotype.Service;

import java.util.List;

@Service
public class AlunoService {
    private final AlunoRepository alunoRepository;

    public AlunoService(AlunoRepository alunoRepository) {
        this.alunoRepository = alunoRepository;
    }
    public Aluno selecionaAluno(Integer id){
        return this.alunoRepository.findById(id).orElseThrow(AlunoNaoEncontradoException::new);
    }
    public List<Aluno> listarTodos(){
        return this.alunoRepository.findAll();
    }
    public void salvarAluno(Aluno aluno){
        this.alunoRepository.save(aluno);
    }
    public void alterarAluno(Integer id, Aluno alunoRequest){
        Aluno aluno = this.selecionaAluno(id);
        aluno.setNome(alunoRequest.getNome());
        this.salvarAluno(aluno);
    }
    public void deletarAluno(Integer id){
        Aluno aluno = selecionaAluno(id);
        this.alunoRepository.delete(aluno);
    }
}
