package com.example.letscode.advice;

import com.example.letscode.exception.AlternativaJaExisteException;
import com.example.letscode.exception.AlternativaNaoEncontradaException;
import com.example.letscode.exception.QuestaoJaExisteException;
import com.example.letscode.exception.QuestaoNaoEncontradaException;
import com.example.letscode.model.Alternativa;
import com.example.letscode.service.AlternativaService;
import com.example.letscode.service.AlunoService;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.validation.FieldError;
import org.springframework.web.bind.MethodArgumentNotValidException;
import org.springframework.web.bind.annotation.ControllerAdvice;
import org.springframework.web.bind.annotation.ExceptionHandler;

import java.util.HashMap;
import java.util.Map;

@ControllerAdvice
public class AlternativaAdvice {

    private static final Logger LOGGER = LoggerFactory.getLogger(AlternativaService.class);

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
