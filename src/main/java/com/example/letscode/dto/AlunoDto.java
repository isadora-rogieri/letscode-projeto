package com.example.letscode.dto;

import lombok.AllArgsConstructor;
import lombok.Getter;
import javax.validation.constraints.NotNull;
import java.time.LocalDate;

@Getter
@AllArgsConstructor
public class AlunoDto {

    @NotNull
    private Integer id;

    @NotNull
    private String nome;

    @NotNull
    private String matricula;

    @NotNull
    private LocalDate dataNascimento;
}
