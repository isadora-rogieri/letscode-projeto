package com.example.letscode.repository;

import com.example.letscode.model.Professor;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.query.Param;
import org.springframework.stereotype.Repository;

import java.util.List;
import java.util.Optional;

@Repository
public interface ProfessorRepository extends JpaRepository<Professor, Integer> {
    Optional<Professor> findProfessorById(Integer id);

    public boolean existsByNome(String nome);
    List<Professor> findByNomeEquals(String nome);
    @Query("SELECT p FROM Professor p WHERE p.nome LIKE %:nome%")
    List<Professor> findProfessorByNome(@Param("nome") String nome);
}
