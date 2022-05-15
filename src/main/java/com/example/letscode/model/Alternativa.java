package com.example.letscode.model;

import lombok.*;
import org.hibernate.annotations.OnDelete;
import org.hibernate.annotations.OnDeleteAction;

import javax.persistence.*;

@AllArgsConstructor
@ToString
@NoArgsConstructor
@Getter
@Setter
@Entity
public class Alternativa {
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Integer id;
    @Column(columnDefinition="TEXT", nullable = false)
    private String descricao;
    @Column(name = "eh_resposta", nullable = false)
    private Boolean ehResposta;
    @ManyToOne
    @JoinColumn(name = "questao_id", nullable = false)
    @OnDelete(action = OnDeleteAction.CASCADE)
    private Questao questao;

    public Alternativa(String descricao, Boolean ehResposta, Questao questao) {
        this.descricao = descricao;
        this.ehResposta = ehResposta;
        this.questao = questao;
    }

}
