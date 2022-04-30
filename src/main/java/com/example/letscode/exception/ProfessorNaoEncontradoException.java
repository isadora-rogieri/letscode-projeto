package com.example.letscode.exception;

public class ProfessorNaoEncontradoException extends RuntimeException{
    public ProfessorNaoEncontradoException(){
        super("Professor não encontrado");
    }
}
