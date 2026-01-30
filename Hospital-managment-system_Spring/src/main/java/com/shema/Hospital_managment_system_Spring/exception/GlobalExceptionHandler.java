package com.shema.Hospital_managment_system_Spring.exception;

import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.MethodArgumentNotValidException;
import org.springframework.web.bind.annotation.ExceptionHandler;
import org.springframework.web.bind.annotation.RestControllerAdvice;

import java.util.HashMap;
import java.util.Map;

@RestControllerAdvice
public class GlobalExceptionHandler {


        @ExceptionHandler(NotFoundException.class)
        public ResponseEntity<Map<String, String>> handleNotFound(NotFoundException ex) {
            Map<String, String> error = new HashMap<>();
            error.put("message", ex.getMessage());
            return ResponseEntity.status(HttpStatus.NOT_FOUND).body(error);
        }

        @ExceptionHandler(BadRequestException.class)
        public ResponseEntity<Map<String, String>> handleBadRequest(BadRequestException ex) {
            Map<String, String> error = new HashMap<>();
            error.put("message", ex.getMessage());
            return ResponseEntity.status(HttpStatus.BAD_REQUEST).body(error);
        }

        @ExceptionHandler(DatabaseException.class)
        public ResponseEntity<Map<String, String>> handleDatabase(DatabaseException ex) {
            Map<String, String> error = new HashMap<>();
            error.put("message", ex.getMessage());
            return ResponseEntity.status(HttpStatus.INTERNAL_SERVER_ERROR).body(error);
        }
         @ExceptionHandler(MethodArgumentNotValidException.class)
        public ResponseEntity<Map<String, String>> handleValidationErrors(
            MethodArgumentNotValidException ex) {

        Map<String, String> errors = new HashMap<>();

        ex.getBindingResult().getFieldErrors().forEach(error -> {
            errors.put(error.getField(), error.getDefaultMessage());
        });

        return ResponseEntity.status(HttpStatus.BAD_REQUEST).body(errors);
        }
}



