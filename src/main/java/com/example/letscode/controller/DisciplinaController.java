package com.example.letscode.controller;

import com.example.letscode.dto.DisciplinaDto;
import com.example.letscode.dto.DtoChange;
import com.example.letscode.model.Disciplina;
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

    @PostMapping
    public ResponseEntity salvar(@Valid @RequestBody DisciplinaDto disciplinaDto){
        Disciplina disciplina = this.dtoChange.DisciplinaDtoToDisciplina(disciplinaDto);
        this.disciplinaService.salvarDisciplina(disciplina);
        ResponseEntity response = new ResponseEntity("Disciplina criada com sucesso", HttpStatus.CREATED);
        return response;
    }

    @GetMapping
    public List<DisciplinaDto> listarDisciplinas(){
        List<DisciplinaDto> list = this.disciplinaService.listarTodos().stream()
                .map(x -> this.dtoChange.DisciplinaToDisciplinaDto(x))
                .collect(Collectors.toList());
        return list;
    }

    @GetMapping("/{id}")
    public ResponseEntity selecionarDisciplinaById(@PathVariable("id") Integer id){
        Disciplina disciplina =  this.disciplinaService.selecionarDisciplinaById(id);
        DisciplinaDto disciplinaDto = this.dtoChange.DisciplinaToDisciplinaDto(disciplina);
        ResponseEntity response = new ResponseEntity(disciplinaDto, HttpStatus.OK);
        return  response;
    }

    @PutMapping("/{id}")
    public ResponseEntity atualizarDisciplina(@PathVariable("id") Integer idDisciplina, @RequestBody DisciplinaDto disciplinaDto){
        Disciplina disciplina = this.dtoChange.DisciplinaDtoToDisciplina(disciplinaDto);
        Disciplina disciplina2 = this.disciplinaService.alterarDisciplina(idDisciplina, disciplina);
        DisciplinaDto disciplinaDtoAtt = this.dtoChange.DisciplinaToDisciplinaDto(disciplina2);
        ResponseEntity response = new ResponseEntity(disciplinaDtoAtt, HttpStatus.OK);
        return  response;
    }

    @DeleteMapping("/{id}")
    public ResponseEntity deletarDisciplina(@PathVariable("id") Integer idDisciplina){
       Disciplina disciplina = this.disciplinaService.deletarDisciplina(idDisciplina);
       ResponseEntity response = new ResponseEntity("Disciplina deletada com sucesso", HttpStatus.OK);
       return  response;
    }


}
