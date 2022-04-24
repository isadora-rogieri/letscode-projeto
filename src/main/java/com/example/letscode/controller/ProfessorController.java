package com.example.letscode.controller;

import com.example.letscode.model.Professor;
import com.example.letscode.service.ProfessorService;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@RestController
@RequestMapping("/professor")
public class ProfessorController {

    private final ProfessorService professorService;

    public ProfessorController(ProfessorService professorService) {
        this.professorService = professorService;
    }

    @PostMapping
    public ResponseEntity salvarProfessor(@RequestBody Professor professor){
        this.professorService.salvarProfessor(professor);
        ResponseEntity response = new ResponseEntity("Professor salvo com sucesso", HttpStatus.CREATED);
        return response;
    }
    @GetMapping
    public List<Professor> listarProfessores(){
        return this.professorService.listarTodos();
    }
    @PutMapping("/{id}")
    public void atualizarProfessor(@PathVariable("id") Integer idProfessor, @RequestBody Professor professor){
        this.professorService.alterarProfessor(idProfessor, professor);
    }
    @DeleteMapping("/{id}")
    public void deletarProfessor(@PathVariable("id") Integer idProfessor){
        this.professorService.deletarProfessor(idProfessor);
    }

}
