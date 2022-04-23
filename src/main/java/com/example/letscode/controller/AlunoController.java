package com.example.letscode.controller;

import com.example.letscode.model.aluno.Aluno;
import com.example.letscode.service.AlunoService;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@RestController
@RequestMapping("/aluno")
public class AlunoController {
    private final AlunoService alunoService;

    public AlunoController(AlunoService alunoService) {
        this.alunoService = alunoService;
    }


    @PostMapping
    public ResponseEntity salvar(@RequestBody Aluno aluno){
        this.alunoService.salvarAluno(aluno);
        ResponseEntity response = new ResponseEntity("Aluno criado com sucesso", HttpStatus.CREATED);
        return response;
    }
    @GetMapping
    public List<Aluno> listarAlunos(){
        return this.alunoService.listarTodos();
    }
    @PutMapping("/{id}")
    public void atualizarAluno(@PathVariable("id") Integer idAluno, @RequestBody Aluno aluno){
        this.alunoService.alterarAluno(idAluno, aluno);
    }
    @DeleteMapping("/{id}")
    public void deletarAluno(@PathVariable("id") Integer idAluno){
        this.alunoService.deletarAluno(idAluno);
    }

}