package com.example.letscode.exception;

public class ProfessorJaExisteException extends RuntimeException{
    public ProfessorJaExisteException(){
        super("Professor já está cadastrado");
    }
}
