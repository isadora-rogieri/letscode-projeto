package com.example.letscode.repository;

import com.example.letscode.model.Aluno;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.query.Param;
import org.springframework.stereotype.Repository;

import java.util.List;
import java.util.Optional;

@Repository
public interface AlunoRepository extends JpaRepository<Aluno, Integer> {
     Optional<Aluno> findAlunoById(Integer id);

    List<Aluno> findByNomeEquals(String nome);

    public Optional<Aluno> findByMatricula(String matricula);

    public boolean existsByMatricula(String matricula);

    @Query("SELECT a.id FROM Aluno a WHERE a.nome LIKE %:nome%")
    List<Integer> findIdByNome(@Param("nome") String nome);

    @Query("SELECT a.nome FROM Aluno a WHERE a.id = :id")
    String findNomeById(@Param("id") Integer id);
}
