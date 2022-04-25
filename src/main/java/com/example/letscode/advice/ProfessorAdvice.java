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
        ResponseEntity response = new ResponseEntity(e.getMessage(), HttpStatus.NOT_FOUND);
        return response;
    }

    @ExceptionHandler
    public ResponseEntity tratarExcecao(ProfessorJaExisteException e){
        ResponseEntity response = new ResponseEntity(e.getMessage(), HttpStatus.UNPROCESSABLE_ENTITY);
        return response;
    }
}

