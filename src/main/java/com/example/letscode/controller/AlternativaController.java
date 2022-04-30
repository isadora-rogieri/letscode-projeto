package com.example.letscode.controller;

import com.example.letscode.model.Alternativa;
import com.example.letscode.model.Questao;
import com.example.letscode.service.AlternativaService;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import javax.validation.Valid;
import java.util.List;

@RestController
@RequestMapping("/alternativas")
public class AlternativaController {

    private final AlternativaService alternativaService;

    public AlternativaController(AlternativaService alternativaService) {
        this.alternativaService = alternativaService;
    }

    @GetMapping
    public List<Alternativa> listarAlterativas() {
        return this.alternativaService.listarAlternativas();
    }

    @GetMapping("{id}")
    public ResponseEntity buscarAlternativaPorId(@PathVariable("id") Integer id) {
        Alternativa alternativa = this.alternativaService.buscarAlternativaPorId(id);
        ResponseEntity response = new ResponseEntity(alternativa, HttpStatus.OK);
        return response;
    }

    @GetMapping("/questao/{questaoId}")
    public List<Alternativa> buscarAlternativasPorQuestaoId(@PathVariable("questaoId") Integer questaoId) {
        return this.alternativaService.buscarAlternativasPorQuestaoId(questaoId);
    }

    @PostMapping
    public ResponseEntity salvarAlternativa(@Valid @RequestBody Alternativa alternativa){
        this.alternativaService.salvarAlternativa(alternativa);
        ResponseEntity response = new ResponseEntity("Alternativa salva com sucesso!" ,HttpStatus.CREATED);
        return response;
    }

    @PutMapping("{id}")
    public ResponseEntity atualizarAlternativa(@PathVariable("id") Integer id, @Valid @RequestBody Alternativa alternativa){
        this.alternativaService.atualizarAlternativa(id, alternativa);
        ResponseEntity response = new ResponseEntity("Alternativa atualizada com sucesso" , HttpStatus.OK);
        return response;
    }

    @DeleteMapping("{id}")
    public ResponseEntity deletarAlternativa(@PathVariable("id") Integer id){
        this.alternativaService.deletarAlternativa(id);
        return ResponseEntity.ok("Questão deletada com sucesso.");
    }
}
