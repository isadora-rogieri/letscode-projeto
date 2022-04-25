package com.example.letscode.exception;

public class AlunoJaExisteException extends RuntimeException{
    public AlunoJaExisteException(){
        super("Aluno já está cadastrado");
    }
}
