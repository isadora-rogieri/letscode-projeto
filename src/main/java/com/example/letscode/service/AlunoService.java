package com.example.letscode.service;

import com.example.letscode.exception.AlunoJaExisteException;
import com.example.letscode.exception.AlunoNaoEncontradoException;
import com.example.letscode.model.Aluno;
import com.example.letscode.repository.AlunoRepository;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.stereotype.Service;

import java.util.List;


@Service
public class AlunoService {
    private final AlunoRepository alunoRepository;
    private final Logger LOGGER = LoggerFactory.getLogger(AlunoService.class);

    public AlunoService(AlunoRepository alunoRepository) {
        this.alunoRepository = alunoRepository;
    }

    public Aluno selecionaAluno(Integer id){
        LOGGER.info("Selecionando aluno pelo ID {}", id);
        return this.alunoRepository.findById(id).orElseThrow(AlunoNaoEncontradoException::new);
    }

    public List<Aluno> listarTodos(){
        return this.alunoRepository.findAll();
    }

    public void salvarAluno(Aluno aluno){
        LOGGER.info("Início do método salvar - Aluno");
        if(!this.alunoRepository.existsByMatricula(aluno.getMatricula())) {
            this.alunoRepository.save(aluno);
        }else{
            throw new AlunoJaExisteException();
        }
        LOGGER.info("Aluno salvo com sucesso");
    }

    public void alterarAluno(Integer id, Aluno alunoRequest){
        LOGGER.info("Início do método alterar - Aluno");
        Aluno aluno = this.selecionaAluno(id);
        aluno.setNome(alunoRequest.getNome());
        this.alunoRepository.save(aluno);
        LOGGER.info("Aluno alterado com sucesso");
    }

    public Aluno deletarAluno(Integer id){
        LOGGER.info("Início do método deletar - Aluno");
        Aluno aluno = selecionaAluno(id);
        this.alunoRepository.delete(aluno);
        LOGGER.info("Aluno deletado com sucesso");
        return aluno;
    }

    public Aluno selecionarAlunoById(Integer id) {
        LOGGER.info("Selecionando aluno pelo ID {}", id);
        return this.alunoRepository.findById(id).orElseThrow(AlunoNaoEncontradoException::new);
    }
}
