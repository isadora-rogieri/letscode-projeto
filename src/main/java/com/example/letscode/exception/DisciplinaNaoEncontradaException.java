package com.example.letscode.exception;

public class DisciplinaNaoEncontradaException extends RuntimeException{
    public DisciplinaNaoEncontradaException(){
        super("Disciplina não encontrada");
    }
}