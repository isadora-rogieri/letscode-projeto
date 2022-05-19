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

    @PostMapping("/salva")
    public ResponseEntity salvarProfessor(@RequestBody Professor professor){
        this.professorService.salvarProfessor(professor);
        return new ResponseEntity("Professor salvo com sucesso", HttpStatus.CREATED);
    }

    @GetMapping
    public List<Professor> listarProfessores(){
        return this.professorService.listarTodos();
    }

    @GetMapping("/{id}")
    public ResponseEntity selecionarProfessorById(@PathVariable("id") Integer idProfessor) {
        var professor = this.professorService.selecionarProfessorById(idProfessor);
        return new ResponseEntity(professor, HttpStatus.OK);
    }

    @PutMapping("/{id}")
    public ResponseEntity atualizarProfessor(@PathVariable("id") Integer idProfessor, @RequestBody Professor professor){
        var professor2 = this.professorService.alterarProfessor(idProfessor, professor);
        return new ResponseEntity(professor2, HttpStatus.OK);
    }

    @DeleteMapping("/{id}")
    public ResponseEntity deletarProfessor(@PathVariable("id") Integer idProfessor){
        this.professorService.deletarProfessor(idProfessor);
        return new ResponseEntity("Professor deletado com sucesso", HttpStatus.OK);
    }
}
