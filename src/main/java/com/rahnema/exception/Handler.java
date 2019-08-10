package com.rahnema.exception;

import io.jsonwebtoken.ExpiredJwtException;
import org.springframework.http.HttpHeaders;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.security.authentication.BadCredentialsException;
import org.springframework.security.authentication.DisabledException;
import org.springframework.security.core.userdetails.UsernameNotFoundException;
import org.springframework.web.bind.annotation.ControllerAdvice;
import org.springframework.web.bind.annotation.ExceptionHandler;
import org.springframework.web.servlet.mvc.method.annotation.ResponseEntityExceptionHandler;

import javax.mail.MessagingException;
import java.io.IOException;

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

    @ExceptionHandler(DisabledException.class)
    public final ResponseEntity<Error> disable(Exception ex) {
        Error errorMessage = (new Error(ex.getMessage(), 2184321));
        return new ResponseEntity<>(errorMessage, new HttpHeaders(), HttpStatus.INTERNAL_SERVER_ERROR);
    }

    @ExceptionHandler(BadCredentialsException.class)
    public final ResponseEntity<Error> badCredential(Exception ex) {
        Error errorMessage = (new Error(ex.getMessage(), 2185302));
        return new ResponseEntity<>(errorMessage, new HttpHeaders(), HttpStatus.INTERNAL_SERVER_ERROR);
    }

    @ExceptionHandler(ExpiredJwtException.class)
    public final ResponseEntity<Error> expireToken(Exception ex) {
        Error errorMessage = (new Error(ex.getMessage(), 6872120));
        return new ResponseEntity<>(errorMessage, new HttpHeaders(), HttpStatus.INTERNAL_SERVER_ERROR);
    }

    @ExceptionHandler(UsernameNotFoundException.class)
    public final ResponseEntity<Error> userNotFound(Exception ex) {
        Error errorMessage = (new Error(ex.getMessage(), 8640212));
        return new ResponseEntity<>(errorMessage, new HttpHeaders(), HttpStatus.NOT_FOUND);
    }

    @ExceptionHandler(IOException.class)
    public final ResponseEntity<Error> io(Exception ex) {
        Error errorMessage = (new Error(ex.getMessage(), 6540321));
        return new ResponseEntity<>(errorMessage, new HttpHeaders(), HttpStatus.NOT_FOUND);
    }


    @ExceptionHandler(MessagingException.class)
    public final ResponseEntity<Error> message(Exception ex) {
        Error errorMessage = (new Error(ex.getMessage(), 4235689));
        return new ResponseEntity<>(errorMessage, new HttpHeaders(), HttpStatus.NOT_FOUND);
    }

}
