package com.ensah.AnnotationApp.Controller;

import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.ControllerAdvice;
import org.springframework.web.bind.annotation.ExceptionHandler;
import org.springframework.web.bind.annotation.ResponseStatus;

@ControllerAdvice
public class GlobalExceptionHandler {

    @ExceptionHandler(Exception.class)
    @ResponseStatus(HttpStatus.INTERNAL_SERVER_ERROR)
    public ResponseEntity<String> handleGeneralException(Exception e) {
        // Log de l'exception
        e.printStackTrace();
        return new ResponseEntity<>("Une erreur interne est survenue.", HttpStatus.INTERNAL_SERVER_ERROR);
    }
    // le programme tente d’accéder à un objet qui n’existe pas (qui n’a pas été initialisé).
    @ExceptionHandler(NullPointerException.class)
    @ResponseStatus(HttpStatus.BAD_REQUEST)
    public ResponseEntity<String> handleNullPointerException(NullPointerException e) {
        // Log de l'exception
        e.printStackTrace();
        return new ResponseEntity<>("Une erreur de données est survenue.", HttpStatus.BAD_REQUEST);
    }

    @ExceptionHandler(IllegalArgumentException.class)
    public ResponseEntity<String> handleIllegalArgumentException(IllegalArgumentException e) {
        // Log de l'exception
        e.printStackTrace();
        return new ResponseEntity<>("Argument invalide fourni.", HttpStatus.BAD_REQUEST);
    }

}

