package com.example.letscode.controller;

import com.example.letscode.model.Questao;
import com.example.letscode.service.QuestaoService;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import javax.validation.Valid;
import java.util.List;

@RestController
@RequestMapping("/questoes")
public class QuestaoController {

    private final QuestaoService questaoService;

    public QuestaoController(QuestaoService questaoService) {
        this.questaoService = questaoService;
    }

    @GetMapping
    public List<Questao> listarQuestoes() {
        return this.questaoService.listarQuestoes();
    }

    @GetMapping("{id}")
    public ResponseEntity buscarQuestaoPorId(@PathVariable("id") Integer id) {
        Questao questao = this.questaoService.buscarQuestaoporId(id);
        ResponseEntity response = new ResponseEntity(questao, HttpStatus.OK);
        return response;
    }

    @PostMapping
    public ResponseEntity salvarQuestao(@Valid @RequestBody Questao questao){
        this.questaoService.salvarQuestao(questao);
        ResponseEntity response = new ResponseEntity("Questão salva com sucesso!", HttpStatus.CREATED);
        return response;
    }

    @PutMapping("{id}")
    public ResponseEntity atualizarQuestao(@PathVariable("id") Integer id, @Valid @RequestBody Questao questao){
        this.questaoService.atualizarQuestao(id, questao);
        ResponseEntity response = new ResponseEntity("Questão atualizada com sucesso", HttpStatus.OK);
        return response;
    }

    @DeleteMapping("{id}")
    public ResponseEntity deletarQuestao(@PathVariable("id") Integer id){
        this.questaoService.deletarQuestao(id);
        return ResponseEntity.ok("Questão deletada com sucesso.");
    }

}
