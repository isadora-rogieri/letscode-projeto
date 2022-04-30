package com.example.letscode.dto;


import lombok.AllArgsConstructor;
import lombok.Getter;

import javax.validation.constraints.NotNull;

@Getter
@AllArgsConstructor
public class AlternativaDto {

    @NotNull
    private Integer id;
    @NotNull
    private String descricao;
    @NotNull
    private Boolean ehResposta;
    @NotNull
    private Integer questao_id;
}
