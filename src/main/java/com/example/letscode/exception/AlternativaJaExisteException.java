package com.example.letscode.exception;

public class AlternativaJaExisteException extends RuntimeException {
    public AlternativaJaExisteException() {
        super("Alternativa já existe");
    }

}
