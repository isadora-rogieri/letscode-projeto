package com.example.letscode.dto;

import com.example.letscode.model.Alternativa;
import com.example.letscode.model.Disciplina;
import com.example.letscode.model.Questao;
import com.example.letscode.service.DisciplinaService;
import com.example.letscode.service.ProfessorService;
import com.example.letscode.service.QuestaoService;
import org.springframework.stereotype.Component;

@Component
public class DtoChange {

    private final DisciplinaService disciplinaService;
    private final QuestaoService questaoService;
    private final ProfessorService professorService;

    public DtoChange(DisciplinaService disciplinaService, QuestaoService questaoService, ProfessorService professorService) {
        this.disciplinaService = disciplinaService;
        this.questaoService = questaoService;
        this.professorService = professorService;
    }

//  Questao
    public Questao questaoDtoToQuestao(QuestaoDto questaoDto) {
        var disciplina = disciplinaService.selecionarDisciplinaById(questaoDto.getDisciplina_id());
        return new Questao(questaoDto.getId(), questaoDto.getEnunciado(), disciplina);
    }
    public QuestaoDto questaoToQuestaoDto(Questao questao) {
        return new QuestaoDto(questao.getId(), questao.getEnunciado(),questao.getDisciplina().getId());
    }

//  Alternativa
    public Alternativa alternativaDtoToAlternativa(AlternativaDto alternativaDto) {
        var questao = questaoService.buscarQuestaoporId(alternativaDto.getQuestao_id());
        return new Alternativa(alternativaDto.getId(), alternativaDto.getDescricao(),
                alternativaDto.getEhResposta(), questao);
    }
    public AlternativaDto alternativaToAlternativaDto(Alternativa alternativa) {
        return new AlternativaDto(alternativa.getId(), alternativa.getDescricao(),
                alternativa.getEhResposta(), alternativa.getQuestao().getId());
    }

//  Disciplina
    public Disciplina disciplinaDtoToDisciplina(DisciplinaDto disciplinaDto) {
        var professor = this.professorService.selecionarProfessor(disciplinaDto.getProfessor_id());
        return new Disciplina(disciplinaDto.getId(), disciplinaDto.getNome(), professor);
    }
    public DisciplinaDto disciplinaToDisciplinaDto(Disciplina disciplina) {
        return new DisciplinaDto(disciplina.getId(),disciplina.getNome(),disciplina.getProfessor().getId());
    }



}
