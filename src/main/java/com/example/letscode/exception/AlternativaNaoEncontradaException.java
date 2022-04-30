package com.example.letscode.exception;

public class AlternativaNaoEncontradaException extends RuntimeException {
    public AlternativaNaoEncontradaException() {
        super("Alternativa não encontrada");
    }

}
