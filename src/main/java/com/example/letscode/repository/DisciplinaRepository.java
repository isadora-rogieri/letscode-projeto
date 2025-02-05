package com.example.letscode.repository;

import com.example.letscode.model.Disciplina;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.query.Param;
import org.springframework.stereotype.Repository;

import java.util.List;
import java.util.Optional;

@Repository
public interface DisciplinaRepository extends JpaRepository<Disciplina, Integer> {
    Optional<Disciplina> findDisciplinaById(Integer id);

    public boolean existsByNome(String nome);
    public boolean existsByProfessorId(Integer id);
    List<Disciplina> findByNomeEquals(String nome);

    @Query("SELECT d.id FROM Disciplina d WHERE d.nome LIKE %:nome%")
    List<Integer> findIdByNome(@Param("nome") String nome);

    @Query("SELECT d.nome, d.professor.nome FROM Disciplina d WHERE d.professor.id = :id")
    List<String> findDisciplinasByProfessorId(@Param("id") Integer id);
}
