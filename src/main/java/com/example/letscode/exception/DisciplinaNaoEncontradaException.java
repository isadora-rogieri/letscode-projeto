package com.example.letscode.exception;

public class DisciplinaNaoEncontradaException extends RuntimeException{
    public DisciplinaNaoEncontradaException(){
        super("Disciplina nao encontrada");
    }
}