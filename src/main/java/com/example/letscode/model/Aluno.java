package com.example.letscode.model;

import com.example.letscode.validator.MatriculaConstraint;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;
import org.hibernate.validator.constraints.Length;

import javax.persistence.*;
import javax.validation.constraints.NotBlank;
import javax.validation.constraints.Pattern;
import java.time.LocalDate;

@NoArgsConstructor
@Getter
@Setter
@Entity
public class Aluno {
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Integer id;

    @Column
    @NotBlank(message = "Nome não informado")
    private String nome;

    @Column
    @NotBlank(message = "Matrícula não informada")
    @Length(max = 10, message = "Quantidade máxima de caracteres excedida")
    @Pattern(regexp = "^MTLA\\d{6}$", message = "Formato da matrícula não aceito - Formato aceito MTLAXXXXXX")
    //@MatriculaConstraint
    private String matricula;

    @Column(name = "data_nascimento", nullable = false)
    private LocalDate dataNascimento;

    public Aluno(String nome, String matricula, LocalDate dataNascimento) {
        this.nome = nome;
        this.matricula = matricula;
        this.dataNascimento = dataNascimento;
    }

    @Override
    public String toString(){
        return "Aluno " + this.nome + "; id: " + this.id + "; matricula: " + this.matricula + "\n\r" +
                "nascido em " + this.dataNascimento.toString() + "\n\r";
    }
}



