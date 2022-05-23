package com.example.letscode.model;

import lombok.*;

import javax.persistence.*;
import javax.validation.constraints.NotBlank;

@AllArgsConstructor
@ToString
@NoArgsConstructor
@Getter
@Setter
@Entity
public class Professor {
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    @NotBlank(message = "ID não informado")
    private Integer id;
    @Column(nullable = false)
    private String nome;

    public Professor(String nome) {
        this.nome = nome;
    }
}

