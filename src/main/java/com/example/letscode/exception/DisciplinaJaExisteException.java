package com.example.letscode.exception;

public class DisciplinaJaExisteException extends RuntimeException{
    public DisciplinaJaExisteException(){
        super("Disciplina já está cadastrada");
    }
}
