package com.example.letscode.advice;

import com.example.letscode.exception.AlternativaJaExisteException;
import com.example.letscode.exception.AlternativaNaoEncontradaException;
import com.example.letscode.service.AlternativaService;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.ControllerAdvice;
import org.springframework.web.bind.annotation.ExceptionHandler;

@ControllerAdvice
public class AlternativaAdvice {

    @ExceptionHandler
    public ResponseEntity tratarAlternativaNaoEncontrada(AlternativaNaoEncontradaException e){
        ResponseEntity response = new ResponseEntity(e.getMessage(), HttpStatus.NOT_FOUND);
        return response;
    }

    @ExceptionHandler
    public ResponseEntity tratarAlternativaJaExistente(AlternativaJaExisteException e){
        ResponseEntity response = new ResponseEntity(e.getMessage(), HttpStatus.CONFLICT);
        return response;
    }
}
