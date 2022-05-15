package com.example.letscode.advice;

import com.example.letscode.exception.AlternativaJaExisteException;
import com.example.letscode.exception.AlternativaNaoEncontradaException;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.ControllerAdvice;
import org.springframework.web.bind.annotation.ExceptionHandler;

@ControllerAdvice
public class AlternativaAdvice {

    @ExceptionHandler
    public ResponseEntity tratarAlternativaNaoEncontrada(AlternativaNaoEncontradaException e){
        return new ResponseEntity(e.getMessage(), HttpStatus.NOT_FOUND);
    }

    @ExceptionHandler
    public ResponseEntity tratarAlternativaJaExistente(AlternativaJaExisteException e){
        return new ResponseEntity(e.getMessage(), HttpStatus.CONFLICT);
    }
}
