package com.example.letscode.exception;

public class QuestaoJaExisteException extends RuntimeException {
    public QuestaoJaExisteException() {
        super("Questão já existe");
    }

}
