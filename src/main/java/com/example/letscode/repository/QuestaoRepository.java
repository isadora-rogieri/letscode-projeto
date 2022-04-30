package com.example.letscode.repository;

import com.example.letscode.model.Questao;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;



@Repository
public interface QuestaoRepository extends JpaRepository<Questao, Integer> {

    boolean existsByEnunciado(String enunciado);
}
