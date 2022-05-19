package com.example.letscode.controller;

import com.example.letscode.dto.AlunoDto;
import com.example.letscode.model.Aluno;
import com.example.letscode.service.AlunoService;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.validation.BindingResult;
import org.springframework.web.bind.annotation.*;
import javax.validation.Valid;

import java.util.List;

@RestController
@RequestMapping("/aluno")
public class AlunoController {
    private final AlunoService alunoService;

    public AlunoController(AlunoService alunoService) {
        this.alunoService = alunoService;
    }

    @PostMapping("/salva")
    public ResponseEntity salvar(@Valid @RequestBody Aluno aluno) {
        this.alunoService.salvarAluno(aluno);
        return new ResponseEntity("Aluno salvo com sucesso", HttpStatus.CREATED);
    }
//    @PostMapping("/salva")
//    public ResponseEntity salvar(@ModelAttribute @Valid @RequestBody AlunoDto alunoDto, BindingResult errors) {
//        Aluno aluno = new Aluno();
//        aluno.setNome(alunoDto.getNome());
//        aluno.setDataNascimento(alunoDto.getDataNascimento());
//        aluno.setMatricula(alunoDto.getMatricula());
//        aluno.setId(alunoDto.getId());
//
//        this.alunoService.salvarAluno(aluno);
//        ResponseEntity response = new ResponseEntity("Aluno salvo com sucesso", HttpStatus.CREATED);
//        return response;
//    }
    @GetMapping
    public List<Aluno> listarAlunos() {
        return this.alunoService.listarTodos();
    }

    @GetMapping("/{id}")
    public ResponseEntity selecionarAlunoById(@PathVariable("id") Integer id) {
        var aluno = this.alunoService.selecionarAlunoById(id);
        return new ResponseEntity(aluno, HttpStatus.OK);
    }

    @PutMapping("/{id}")
    public void atualizarAluno(@PathVariable("id") Integer idAluno, @RequestBody Aluno aluno) {
        this.alunoService.alterarAluno(idAluno, aluno);
    }

    @DeleteMapping("/{id}")
    public ResponseEntity deletarAluno(@PathVariable("id") Integer idAluno) {
        this.alunoService.deletarAluno(idAluno);
        return new ResponseEntity("Aluno deletado com sucesso", HttpStatus.OK);
    }
}