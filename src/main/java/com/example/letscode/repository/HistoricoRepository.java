package com.example.letscode.repository;

import com.example.letscode.model.historico.Historico;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

@Repository
public interface HistoricoRepository extends JpaRepository<Historico, Integer> {
    Historico findHistoricoById(Integer id);
}
