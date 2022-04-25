package com.example.letscode.service;

import com.example.letscode.exception.AlunoJaExisteException;
import com.example.letscode.exception.AlunoNaoEncontradoException;
import com.example.letscode.model.Aluno;
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
        if(!this.alunoRepository.existsByMatricula(aluno.getMatricula())) {
            this.alunoRepository.save(aluno);
        }else{
            throw new AlunoJaExisteException();
        }
    }
    public void alterarAluno(Integer id, Aluno alunoRequest){
        Aluno aluno = this.selecionaAluno(id);
        aluno.setNome(alunoRequest.getNome());
        this.salvarAluno(aluno);
    }
    public Aluno deletarAluno(Integer id){
        Aluno aluno = selecionaAluno(id);
        this.alunoRepository.delete(aluno);
        return aluno;
    }

    public Aluno selecionarAlunoById(Integer id) {
        return this.alunoRepository.findById(id).orElseThrow(AlunoNaoEncontradoException::new);
    }
}
