package com.example.letscode.model;

import lombok.AllArgsConstructor;
import lombok.Getter;

import java.util.List;

@Getter
@AllArgsConstructor
public class QuestaoAlternativaResposta {
    private String questao;
    private List<AlternativaResposta> alternativas;
}
