package com.example.letscode.repository;

import com.example.letscode.model.Questao;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

import java.util.List;

@Repository
public interface QuestaoRepository extends JpaRepository<Questao, Integer> {
    Questao findQuestaoById(Integer id);
    List<Questao> findQuestaoByEnunciadoEquals(String enunciado);
}
