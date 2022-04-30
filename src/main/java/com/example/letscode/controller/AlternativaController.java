package com.example.letscode.controller;

import com.example.letscode.dto.AlternativaDto;
import com.example.letscode.dto.DtoChange;
import com.example.letscode.model.Alternativa;
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
        List<AlternativaDto> list = this.alternativaService.listarAlternativas().stream()
                .map(x -> this.dtoChange.AlternativaToAlternativaDto(x))
                .collect(Collectors.toList());

        return list;
    }

    @GetMapping("/{id}")
    public ResponseEntity buscarAlternativaPorId(@PathVariable("id") Integer id) {
        Alternativa alternativa = this.alternativaService.buscarAlternativaPorId(id);
        AlternativaDto alternativaDto = this.dtoChange.AlternativaToAlternativaDto(alternativa);
        ResponseEntity response = new ResponseEntity(alternativaDto, HttpStatus.OK);
        return response;
    }

    @GetMapping("/questao/{questaoId}")
    public List<AlternativaDto> buscarAlternativasPorQuestaoId(@PathVariable("questaoId") Integer questaoId) {
        List<AlternativaDto> list = this.alternativaService.buscarAlternativasPorQuestaoId(questaoId).stream()
                .map(x -> this.dtoChange.AlternativaToAlternativaDto(x))
                .collect(Collectors.toList());
        return list;
    }

    @PostMapping
    public ResponseEntity salvarAlternativa(@Valid @RequestBody AlternativaDto alternativaDto){
        Alternativa alternativa = this.dtoChange.AlternativaDtoToAlternativa(alternativaDto);
        this.alternativaService.salvarAlternativa(alternativa);
        ResponseEntity response = new ResponseEntity("Alternativa salva com sucesso!", HttpStatus.CREATED);
        return response;
    }

    @PutMapping("/{id}")
    public ResponseEntity atualizarAlternativa(@PathVariable("id") Integer id, @Valid @RequestBody AlternativaDto alternativaDto){
        Alternativa alternativa = this.dtoChange.AlternativaDtoToAlternativa(alternativaDto);
        this.alternativaService.atualizarAlternativa(id, alternativa);
        ResponseEntity response = new ResponseEntity("Alternativa atualizada com sucesso", HttpStatus.OK);
        return response;
    }

    @DeleteMapping("{id}")
    public ResponseEntity deletarAlternativa(@PathVariable("id") Integer id){
        this.alternativaService.deletarAlternativa(id);
        return ResponseEntity.ok("Questão deletada com sucesso.");
    }
}
