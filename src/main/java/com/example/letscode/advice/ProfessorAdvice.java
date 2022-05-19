package com.example.letscode.advice;

import com.example.letscode.exception.ProfessorJaExisteException;
import com.example.letscode.exception.ProfessorNaoEncontradoException;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.ControllerAdvice;
import org.springframework.web.bind.annotation.ExceptionHandler;

@ControllerAdvice
public class ProfessorAdvice {

    @ExceptionHandler
    public ResponseEntity tratarExcecao(ProfessorNaoEncontradoException e){
        return new ResponseEntity(e.getMessage(), HttpStatus.NOT_FOUND);
    }

    @ExceptionHandler
    public ResponseEntity tratarExcecao(ProfessorJaExisteException e){
        return new ResponseEntity(e.getMessage(), HttpStatus.UNPROCESSABLE_ENTITY);
    }
}

