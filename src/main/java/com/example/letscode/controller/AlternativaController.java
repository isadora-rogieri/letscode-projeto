package com.example.letscode.controller;

import com.example.letscode.dto.AlternativaDto;
import com.example.letscode.dto.DtoChange;
import com.example.letscode.service.AlternativaService;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import javax.validation.Valid;
import java.util.List;
import java.util.stream.Collectors;

@RestController
@RequestMapping("/alternativas")
public class AlternativaController {

    private final AlternativaService alternativaService;
    private final DtoChange dtoChange;

    public AlternativaController(AlternativaService alternativaService, DtoChange dtoChange) {
        this.alternativaService = alternativaService;
        this.dtoChange = dtoChange;
    }

    @GetMapping
    public List<AlternativaDto> listarAlterativas() {
        return this.alternativaService.listarAlternativas().stream()
                .map(this.dtoChange::alternativaToAlternativaDto)
                .collect(Collectors.toList());
    }

    @GetMapping("/{id}")
    public ResponseEntity buscarAlternativaPorId(@PathVariable("id") Integer id) {
        var alternativa = this.alternativaService.buscarAlternativaPorId(id);
        var alternativaDto = this.dtoChange.alternativaToAlternativaDto(alternativa);
        return new ResponseEntity(alternativaDto, HttpStatus.OK);
    }

    @GetMapping("/questao/{questaoId}")
    public List<AlternativaDto> buscarAlternativasPorQuestaoId(@PathVariable("questaoId") Integer questaoId) {
        return this.alternativaService.buscarAlternativasPorQuestaoId(questaoId).stream()
                .map(this.dtoChange::alternativaToAlternativaDto)
                .collect(Collectors.toList());
    }

    @PostMapping
    public ResponseEntity salvarAlternativa(@Valid @RequestBody AlternativaDto alternativaDto){
        var alternativa = this.dtoChange.alternativaDtoToAlternativa(alternativaDto);
        this.alternativaService.salvarAlternativa(alternativa);
        return new ResponseEntity("Alternativa salva com sucesso!", HttpStatus.CREATED);
    }

    @PutMapping("/{id}")
    public ResponseEntity atualizarAlternativa(@PathVariable("id") Integer id, @Valid @RequestBody AlternativaDto alternativaDto){
        var alternativa = this.dtoChange.alternativaDtoToAlternativa(alternativaDto);
        this.alternativaService.atualizarAlternativa(id, alternativa);
        return new ResponseEntity("Alternativa atualizada com sucesso", HttpStatus.OK);
    }

    @DeleteMapping("{id}")
    public ResponseEntity deletarAlternativa(@PathVariable("id") Integer id){
        this.alternativaService.deletarAlternativa(id);
        return ResponseEntity.ok("Questão deletada com sucesso.");
    }
}
