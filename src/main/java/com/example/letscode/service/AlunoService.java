package com.example.letscode.service;

import com.example.letscode.exception.AlunoJaExisteException;
import com.example.letscode.exception.AlunoNaoEncontradoException;
import com.example.letscode.model.Aluno;
import com.example.letscode.repository.AlunoRepository;
import org.slf4j.LoggerFactory;
import org.springframework.stereotype.Service;

import java.util.List;
import java.util.logging.Logger;

@Service
public class AlunoService {
    private final AlunoRepository alunoRepository;
//    private final Logger LOGGER = (Logger) LoggerFactory.getLogger(AlunoService.class);

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
//        LOGGER.info("Início do método salvar");
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
