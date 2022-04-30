package com.example.letscode.advice;

import com.example.letscode.exception.QuestaoJaExisteException;
import com.example.letscode.exception.QuestaoNaoEncontradaException;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.ControllerAdvice;
import org.springframework.web.bind.annotation.ExceptionHandler;

@ControllerAdvice
public class QuestaoAdvice {

    @ExceptionHandler
    public ResponseEntity tratarQuestaoNaoEncontrada(QuestaoNaoEncontradaException e){
        ResponseEntity response = new ResponseEntity(e.getMessage(), HttpStatus.NOT_FOUND);
        return response;
    }

    @ExceptionHandler
    public ResponseEntity tratarQuestaoJaExistente(QuestaoJaExisteException e){
        ResponseEntity response = new ResponseEntity(e.getMessage(), HttpStatus.CONFLICT);
        return response;
    }
}
