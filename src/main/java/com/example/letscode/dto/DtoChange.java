package com.example.letscode.dto;

import com.example.letscode.model.Alternativa;
import com.example.letscode.model.Disciplina;
import com.example.letscode.model.Professor;
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
    public Questao QuestaoDtoToQuestao(QuestaoDto questaoDto) {
        Disciplina disciplina = disciplinaService.selecionarDisciplinaById(questaoDto.getDisciplina_id());
        Questao questao = new Questao(questaoDto.getId(), questaoDto.getEnunciado(), disciplina);
        return questao;
    }
    public QuestaoDto QuestaoToQuestaoDto(Questao questao) {
        QuestaoDto questaoDto = new QuestaoDto(questao.getId(), questao.getEnunciado(),questao.getDisciplina().getId());
        return questaoDto;
    }

//  Alternativa
    public Alternativa AlternativaDtoToAlternativa(AlternativaDto alternativaDto) {
        Questao questao = questaoService.buscarQuestaoporId(alternativaDto.getQuestao_id());
        Alternativa alternativa = new Alternativa(alternativaDto.getId(), alternativaDto.getDescricao(),
                alternativaDto.getEhResposta(), questao);
        return alternativa;
    }
    public AlternativaDto AlternativaToAlternativaDto(Alternativa alternativa) {
        AlternativaDto alternativaDto = new AlternativaDto(alternativa.getId(), alternativa.getDescricao(),
                alternativa.getEhResposta(), alternativa.getQuestao().getId());
        return alternativaDto;
    }

//  Disciplina
    public Disciplina DisciplinaDtoToDisciplina(DisciplinaDto disciplinaDto) {
        Professor professor = this.professorService.selecionarProfessor(disciplinaDto.getProfessor_id());
        Disciplina disciplina = new Disciplina(disciplinaDto.getId(), disciplinaDto.getNome(), professor);
        return disciplina;
    }
    public DisciplinaDto DisciplinaToDisciplinaDto(Disciplina disciplina) {
        DisciplinaDto disciplinaDto = new DisciplinaDto(disciplina.getId(),disciplina.getNome(),disciplina.getProfessor().getId());
        return disciplinaDto;
    }



}
