package com.example.letscode.dto;

import lombok.AllArgsConstructor;
import lombok.Getter;

import javax.validation.constraints.NotNull;

@Getter
@AllArgsConstructor
public class DisciplinaDto {

    @NotNull
    private Integer id;
    @NotNull
    private String nome;
    @NotNull
    private Integer professor_id;
}
