package com.example.letscode.advice;

import com.example.letscode.exception.AlunoNaoEncontradoException;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.ControllerAdvice;
import org.springframework.web.bind.annotation.ExceptionHandler;

@ControllerAdvice
public class AlunoAdvice {

    @ExceptionHandler
    public ResponseEntity tratarExcecao(AlunoNaoEncontradoException e){
        ResponseEntity response = new ResponseEntity(e.getMessage(), HttpStatus.NOT_FOUND);
        return response;
    }
}

