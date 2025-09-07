package com.learning.quiz.exception;

import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.ControllerAdvice;
import org.springframework.web.bind.annotation.ExceptionHandler;

@ControllerAdvice
public class QuizExceptionHandler {

    @ExceptionHandler(value= QuizNotFoundException.class)
    public ResponseEntity<Object> quizNotFoundExceptionHandler(QuizNotFoundException e){
        System.out.println("inside quizNotFoundExceptionHandler");
        QuizGlobalException quizException=new QuizGlobalException(e.getMessage(), e.getCause(), HttpStatus.NOT_FOUND);
        return new ResponseEntity<>(quizException, HttpStatus.NOT_FOUND);
    }
}
