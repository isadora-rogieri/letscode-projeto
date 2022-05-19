package com.example.letscode.advice;

import com.example.letscode.exception.DisciplinaJaExisteException;
import com.example.letscode.exception.DisciplinaNaoEncontradaException;
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
public class DisciplinaAdvice {
    private final Logger LOGGER = LoggerFactory.getLogger(DisciplinaAdvice.class);

    @ExceptionHandler
    public ResponseEntity tratarExcecao(DisciplinaNaoEncontradaException e){
        return new ResponseEntity(e.getMessage(), HttpStatus.NOT_FOUND);
    }

    @ExceptionHandler
    public ResponseEntity tratarExcecao(DisciplinaJaExisteException e){
        return new ResponseEntity(e.getMessage(), HttpStatus.CONFLICT);
    }

    @ExceptionHandler
    public ResponseEntity tratarRestricoesBean(MethodArgumentNotValidException e){
        Map<String, String> erros = new HashMap<>();

        for (var indice = 0; indice < e.getBindingResult().getAllErrors().size(); indice++){

            String fieldName =  ((FieldError) e.getBindingResult().getAllErrors().get(indice)).getField();
            String errorMessage = ((FieldError) e.getBindingResult().getAllErrors().get(indice)).getDefaultMessage();
            var erroFormatado = String.format("Erro no campo %s - %s",fieldName,errorMessage);
            erros.put(String.format("Erro %s ",indice ), String.format("Erro no campo %s - %s",fieldName,errorMessage));
            LOGGER.debug(erroFormatado);
        }

        return new ResponseEntity(erros, HttpStatus.BAD_REQUEST);
    }
}
