package com.example.letscode.exception;

public class AlunoNaoEncontradoException extends RuntimeException{
    public AlunoNaoEncontradoException(){
        super("Aluno nao encontrado");
    }
}
