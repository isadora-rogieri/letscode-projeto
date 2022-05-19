package com.example.letscode.controller;

import com.example.letscode.dto.DisciplinaDto;
import com.example.letscode.dto.DtoChange;
import com.example.letscode.service.DisciplinaService;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import javax.validation.Valid;
import java.util.List;
import java.util.stream.Collectors;

@RestController
@RequestMapping("/disciplina")
public class DisciplinaController {

    private final DisciplinaService disciplinaService;
    private final DtoChange dtoChange;

    public DisciplinaController(DisciplinaService disciplinaService, DtoChange dtoChange) {
        this.disciplinaService = disciplinaService;
        this.dtoChange = dtoChange;
    }

    @PostMapping("/salva")
    public ResponseEntity salvar(@Valid @RequestBody DisciplinaDto disciplinaDto){
        var disciplina = this.dtoChange.disciplinaDtoToDisciplina(disciplinaDto);
        this.disciplinaService.salvarDisciplina(disciplina);
        return new ResponseEntity("Disciplina criada com sucesso", HttpStatus.CREATED);
    }

    @GetMapping
    public List<DisciplinaDto> listarDisciplinas(){
        return this.disciplinaService.listarTodos().stream()
                .map(this.dtoChange::DisciplinaToDisciplinaDto)
                .collect(Collectors.toList());
    }

    @GetMapping("/{id}")
    public ResponseEntity selecionarDisciplinaById(@PathVariable("id") Integer id){
        var disciplina =  this.disciplinaService.selecionarDisciplinaById(id);
        var disciplinaDto = this.dtoChange.DisciplinaToDisciplinaDto(disciplina);
        return new ResponseEntity(disciplinaDto, HttpStatus.OK);
    }

    @PutMapping("/{id}")
    public ResponseEntity atualizarDisciplina(@PathVariable("id") Integer idDisciplina, @RequestBody DisciplinaDto disciplinaDto){
        var disciplina = this.dtoChange.disciplinaDtoToDisciplina(disciplinaDto);
        var disciplina2 = this.disciplinaService.alterarDisciplina(idDisciplina, disciplina);
        var disciplinaDtoAtt = this.dtoChange.DisciplinaToDisciplinaDto(disciplina2);
        return new ResponseEntity(disciplinaDtoAtt, HttpStatus.OK);
    }

    @DeleteMapping("/{id}")
    public ResponseEntity deletarDisciplina(@PathVariable("id") Integer idDisciplina){
       this.disciplinaService.deletarDisciplina(idDisciplina);
       return new ResponseEntity("Disciplina deletada com sucesso", HttpStatus.OK);
    }


}
