package com.example.letscode.controller;

import com.example.letscode.dto.DtoChange;
import com.example.letscode.dto.QuestaoDto;
import com.example.letscode.service.QuestaoService;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import javax.validation.Valid;
import java.util.List;
import java.util.stream.Collectors;

@RestController
@RequestMapping("/questoes")
public class QuestaoController {

    private final QuestaoService questaoService;
    private final DtoChange dtoChange;

    public QuestaoController(QuestaoService questaoService, com.example.letscode.dto.DtoChange dtoChange) {
        this.questaoService = questaoService;
        this.dtoChange = dtoChange;
    }

    @GetMapping
    public List<QuestaoDto> listarQuestoes() {
        return this.questaoService.listarQuestoes().stream()
                .map(this.dtoChange::questaoToQuestaoDto)
                .collect(Collectors.toList());
    }

    @GetMapping("{id}")
    public ResponseEntity buscarQuestaoPorId(@PathVariable("id") Integer id) {
        var questao = this.questaoService.buscarQuestaoporId(id);
        QuestaoDto questaoDto = this.dtoChange.questaoToQuestaoDto(questao);
        return new ResponseEntity(questaoDto, HttpStatus.OK);
    }

    @PostMapping
    public ResponseEntity salvarQuestao(@Valid @RequestBody QuestaoDto questaoDto){
        var questao = this.dtoChange.questaoDtoToQuestao(questaoDto);
        this.questaoService.salvarQuestao(questao);
        return new ResponseEntity("Questão salva com sucesso!", HttpStatus.CREATED);
    }

    @PutMapping("{id}")
    public ResponseEntity atualizarQuestao(@PathVariable("id") Integer id, @Valid @RequestBody QuestaoDto questaoDto){
        var questao = this.dtoChange.questaoDtoToQuestao(questaoDto);
        this.questaoService.atualizarQuestao(id, questao);
        return new ResponseEntity("Questão atualizada com sucesso", HttpStatus.OK);
    }

    @DeleteMapping("{id}")
    public ResponseEntity deletarQuestao(@PathVariable("id") Integer id){
        this.questaoService.deletarQuestao(id);
        return ResponseEntity.ok("Questão deletada com sucesso.");
    }

}
