package com.example.letscode.repository;

import com.example.letscode.model.Questao;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

import java.util.Optional;


@Repository
public interface QuestaoRepository extends JpaRepository<Questao, Integer> {

    @Override
    Optional<Questao> findById(Integer integer);

    boolean existsByEnunciado(String enunciado);
}
