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
        Error errorMessage = (new Error(ex.getMessage(), 10670445));
        return new ResponseEntity<>(errorMessage, new HttpHeaders(), HttpStatus.INTERNAL_SERVER_ERROR);
    }

    @ExceptionHandler(WrongArgumantException.class)
    public final ResponseEntity<Error> wrongArgumant(Exception ex) {
        Error errorMessage = (new Error(ex.getMessage(), 8536356));
        return new ResponseEntity<>(errorMessage, new HttpHeaders(), HttpStatus.INTERNAL_SERVER_ERROR);
    }

    @ExceptionHandler(FileNotExistException.class)
    public final ResponseEntity<Error> notFound(Exception ex) {
        Error errorMessage = (new Error(ex.getMessage(), 6402267));
        return new ResponseEntity<>(errorMessage, new HttpHeaders(), HttpStatus.NOT_FOUND);
    }

    @ExceptionHandler(StorageFileNotFoundException.class)
    public final ResponseEntity<Error> storage(Exception ex) {
        Error errorMessage = (new Error(ex.getMessage(), 4785170));
        return new ResponseEntity<>(errorMessage, new HttpHeaders(), HttpStatus.NOT_FOUND);
    }

    @ExceptionHandler(EmailAlreadyExistException.class)
    public final ResponseEntity<Error> emailValidation(Exception ex) {
        Error errorMessage = (new Error(ex.getMessage(), 2134089));
        return new ResponseEntity<>(errorMessage, new HttpHeaders(), HttpStatus.FOUND);
    }

}
