package com.rahnema.exception;

import org.springframework.http.HttpHeaders;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.ControllerAdvice;
import org.springframework.web.bind.annotation.ExceptionHandler;
import org.springframework.web.servlet.mvc.method.annotation.ResponseEntityExceptionHandler;

@ControllerAdvice
public class Handler extends ResponseEntityExceptionHandler {

    @ExceptionHandler(NullValueException.class)
    public final ResponseEntity<Error> nullPointer(Exception ex) {
        Error errorMessage = (new Error(ex.getMessage(), 700));
        return new ResponseEntity<>(errorMessage, new HttpHeaders(), HttpStatus.INTERNAL_SERVER_ERROR);
    }

    @ExceptionHandler(WrongArgumantException.class)
    public final ResponseEntity<Error> wrongArgumant(Exception ex) {
        Error errorMessage = (new Error(ex.getMessage(), 500));
        return new ResponseEntity<>(errorMessage, new HttpHeaders(), HttpStatus.INTERNAL_SERVER_ERROR);
    }

    @ExceptionHandler(FileNotExistException.class)
    public final ResponseEntity<Error> notFound(Exception ex) {
        Error errorMessage = (new Error(ex.getMessage(), 300));
        return new ResponseEntity<>(errorMessage, new HttpHeaders(), HttpStatus.INTERNAL_SERVER_ERROR);
    }


}
