package com.example.letscode.exception;

public class DisciplniaNaoEncontradaException extends RuntimeException{
    public DisciplniaNaoEncontradaException(){
        super("Disciplina nao encontrada");
    }
}
