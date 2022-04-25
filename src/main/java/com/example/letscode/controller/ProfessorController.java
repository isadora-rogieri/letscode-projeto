package com.example.letscode.controller;

import com.example.letscode.model.Aluno;
import com.example.letscode.model.Disciplina;
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

    @GetMapping("/{id}")
    public ResponseEntity selecionarProfessorById(@PathVariable("id") Integer idProfessor) {
        Professor professor = this.professorService.selecionarProfessorById(idProfessor);
        ResponseEntity response = new ResponseEntity(professor, HttpStatus.OK);
        return response;
    }

    @PutMapping("/{id}")
    public ResponseEntity atualizarProfessor(@PathVariable("id") Integer idProfessor, @RequestBody Professor professor){
        Professor professor2 = this.professorService.alterarProfessor(idProfessor, professor);
        ResponseEntity response = new ResponseEntity(professor2, HttpStatus.OK);
        return  response;
    }

    @DeleteMapping("/{id}")
    public ResponseEntity deletarProfessor(@PathVariable("id") Integer idProfessor){
        Professor professor = this.professorService.deletarProfessor(idProfessor);
        ResponseEntity response = new ResponseEntity("Professor deletado com sucesso", HttpStatus.OK);
        return response;
    }
}
