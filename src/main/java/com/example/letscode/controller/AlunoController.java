package com.example.letscode.controller;

import com.example.letscode.model.Aluno;
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
    public ResponseEntity salvar(@RequestBody Aluno aluno) {
        this.alunoService.salvarAluno(aluno);
        ResponseEntity response = new ResponseEntity("Aluno criado com sucesso", HttpStatus.CREATED);
        return response;
    }

    @GetMapping
    public List<Aluno> listarAlunos() {
        return this.alunoService.listarTodos();
    }

    @GetMapping("{id}")
    public ResponseEntity selecionarDisciplinaById(@PathVariable("id") Integer id) {
        Aluno aluno = this.alunoService.selecionarAlunoById(id);
        ResponseEntity response = new ResponseEntity(aluno, HttpStatus.OK);
        return response;

    }

    @PutMapping("/{id}")
    public void atualizarAluno(@PathVariable("id") Integer idAluno, @RequestBody Aluno aluno) {
        this.alunoService.alterarAluno(idAluno, aluno);
    }

    @DeleteMapping("/{id}")
    public ResponseEntity deletarAluno(@PathVariable("id") Integer idAluno) {
        Aluno aluno = this.alunoService.deletarAluno(idAluno);
        ResponseEntity response = new ResponseEntity("Aluno deletado com sucesso", HttpStatus.OK);
        return response;
    }
}