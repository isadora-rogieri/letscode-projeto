package com.example.letscode.controller;

import com.example.letscode.dto.DtoChange;
import com.example.letscode.dto.QuestaoDto;
import com.example.letscode.model.Questao;
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
        List<QuestaoDto> list = this.questaoService.listarQuestoes().stream()
                .map(x -> this.dtoChange.QuestaoToQuestaoDto(x))
                .collect(Collectors.toList());
        return list;
    }

    @GetMapping("{id}")
    public ResponseEntity buscarQuestaoPorId(@PathVariable("id") Integer id) {
        Questao questao = this.questaoService.buscarQuestaoporId(id);
        QuestaoDto questaoDto = this.dtoChange.QuestaoToQuestaoDto(questao);
        ResponseEntity response = new ResponseEntity(questaoDto, HttpStatus.OK);
        return response;
    }

    @PostMapping
    public ResponseEntity salvarQuestao(@Valid @RequestBody QuestaoDto questaoDto){
        Questao questao = this.dtoChange.QuestaoDtoToQuestao(questaoDto);
        this.questaoService.salvarQuestao(questao);
        ResponseEntity response = new ResponseEntity("Questão salva com sucesso!", HttpStatus.CREATED);
        return response;
    }

    @PutMapping("{id}")
    public ResponseEntity atualizarQuestao(@PathVariable("id") Integer id, @Valid @RequestBody QuestaoDto questaoDto){
        Questao questao = this.dtoChange.QuestaoDtoToQuestao(questaoDto);
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
