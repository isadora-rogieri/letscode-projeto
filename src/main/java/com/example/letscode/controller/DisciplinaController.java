package com.example.letscode.controller;

import com.example.letscode.model.Disciplina;
import com.example.letscode.service.DisciplinaService;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@RestController
@RequestMapping("/disciplina")
public class DisciplinaController {

    private final DisciplinaService disciplinaService;

    public DisciplinaController(DisciplinaService disciplinaService) {
        this.disciplinaService = disciplinaService;
    }

    @PostMapping
    public ResponseEntity salvar(@RequestBody Disciplina disciplina){
        this.disciplinaService.salvarDisciplina(disciplina);
        ResponseEntity response = new ResponseEntity("Disciplina criada com sucesso", HttpStatus.CREATED);
        return response;
    }

    @GetMapping
    public List<Disciplina> listarDisciplinas(){
        return this.disciplinaService.listarTodos();
    }

    @GetMapping("/{id}")
    public ResponseEntity selecionarDisciplinaById(@PathVariable("id") Integer id){
        Disciplina disciplina =  this.disciplinaService.selecionarDisciplinaById(id);
        ResponseEntity response = new ResponseEntity(disciplina, HttpStatus.OK);
        return  response;
    }

    @PutMapping("/{id}")
    public ResponseEntity atualizarDisciplina(@PathVariable("id") Integer idDisciplina, @RequestBody Disciplina disciplina){
        Disciplina disciplina2 = this.disciplinaService.alterarDisciplina(idDisciplina, disciplina);
        ResponseEntity response = new ResponseEntity(disciplina, HttpStatus.OK);
        return  response;
    }

    @DeleteMapping("/{id}")
    public ResponseEntity deletarDisciplina(@PathVariable("id") Integer idDisciplina){
       Disciplina disciplina = this.disciplinaService.deletarDisciplina(idDisciplina);
       ResponseEntity response = new ResponseEntity("Disciplina deletada com sucesso", HttpStatus.OK);
       return  response;
    }


}
