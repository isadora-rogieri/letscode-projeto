package com.example.letscode.exception;

public class QuestaoNaoEncontradaException extends RuntimeException {
    public QuestaoNaoEncontradaException() {
        super("Questão não encontrada");
    }
}
