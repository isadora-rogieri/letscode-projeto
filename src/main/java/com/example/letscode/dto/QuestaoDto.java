package com.example.letscode.dto;

import lombok.AllArgsConstructor;
import lombok.Getter;

import javax.validation.constraints.NotNull;

@AllArgsConstructor
@Getter
public class QuestaoDto {

    @NotNull
    private Integer id;
    @NotNull
    private String enunciado;
    @NotNull
    private Integer disciplina_id;

}
