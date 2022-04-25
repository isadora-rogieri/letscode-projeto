package com.example.letscode.advice;

import com.example.letscode.exception.DisciplinaJaExisteException;
import com.example.letscode.exception.DisciplinaNaoEncontradaException;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.ControllerAdvice;
import org.springframework.web.bind.annotation.ExceptionHandler;

@ControllerAdvice
public class DisciplinaAdvice {

    @ExceptionHandler
    public ResponseEntity tratarExcecao(DisciplinaNaoEncontradaException e){
        ResponseEntity response = new ResponseEntity(e.getMessage(), HttpStatus.NOT_FOUND);
        return  response;
    }

    @ExceptionHandler
    public ResponseEntity tratarExcecao(DisciplinaJaExisteException e){
        ResponseEntity response = new ResponseEntity(e.getMessage(), HttpStatus.UNPROCESSABLE_ENTITY);
        return  response;
    }
}
