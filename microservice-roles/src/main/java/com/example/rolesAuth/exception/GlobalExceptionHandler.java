package com.example.rolesAuth.exception;

import java.time.LocalDateTime;

import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.security.core.userdetails.UsernameNotFoundException;
import org.springframework.web.bind.annotation.ExceptionHandler;
import org.springframework.web.bind.annotation.RestControllerAdvice;

import jakarta.persistence.EntityNotFoundException;

@RestControllerAdvice
public class GlobalExceptionHandler {

    @ExceptionHandler(EntityNotFoundException.class)
    public ResponseEntity<ErrorExceptionResponse> handlerEntityNotFound(EntityNotFoundException e) {
        ErrorExceptionResponse error = new ErrorExceptionResponse(
                HttpStatus.NOT_FOUND.value(),
                e.getMessage(),
                LocalDateTime.now());

        return ResponseEntity.status(HttpStatus.NOT_FOUND).body(error);
    }

    @ExceptionHandler(EmailAlreadyExistsException.class)
    public ResponseEntity<ErrorExceptionResponse> handlerDataIntegrityViolation(EmailAlreadyExistsException e) {
        ErrorExceptionResponse error = new ErrorExceptionResponse(
                HttpStatus.CONFLICT.value(),
                e.getMessage(),
                LocalDateTime.now());
        return ResponseEntity.status(HttpStatus.CONFLICT).body(error);
    }

    @ExceptionHandler(UsernameNotFoundException.class)
    public ResponseEntity<ErrorExceptionResponse> handlerUserNotFoundException(UsernameNotFoundException e) {
        ErrorExceptionResponse error = new ErrorExceptionResponse(
                HttpStatus.NOT_FOUND.value(),
                e.getMessage(),
                LocalDateTime.now());
        return ResponseEntity.status(HttpStatus.NOT_FOUND).body(error);
    }
}
