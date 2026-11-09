package com.kelvin.spring_restapi;

import com.kelvin.spring_restapi.customExceptions.DuplicateEntryException;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.MethodArgumentNotValidException;
import org.springframework.web.bind.annotation.ExceptionHandler;
import org.springframework.web.bind.annotation.RestControllerAdvice;
import org.springframework.web.server.ResponseStatusException;

import java.util.LinkedHashMap;
import java.util.Map;
import java.util.Optional;

@RestControllerAdvice
public class GlobalExceptionHandler {
    private static final Logger log = LoggerFactory.getLogger(GlobalExceptionHandler.class);

    @ExceptionHandler(ResponseStatusException.class)
    public ResponseEntity<Map<String,Object>> handleException(ResponseStatusException ex){
        String message=ex.getReason() != null ? ex.getReason() : "Request failed";
        return build(HttpStatus.valueOf(ex.getStatusCode().value()),message);
    }


    @ExceptionHandler(DuplicateEntryException.class)
    public ResponseEntity<Map<String,Object>> handleIntegrity(DuplicateEntryException ex){
        log.warn("Data integrity violation: {}",ex.getMessage());
        return build(HttpStatus.CONFLICT,ex.getMessage());

    }

    @ExceptionHandler(MethodArgumentNotValidException.class)
    public ResponseEntity<Map<String,Object>> handleValidation(MethodArgumentNotValidException error){

        String errorMessage=error.getBindingResult().getFieldErrors().stream().findFirst().map(e->e.getDefaultMessage()).orElse("Validation failed");
        log.warn("Validation failed: {}",errorMessage);
        return build(HttpStatus.BAD_REQUEST, errorMessage);
    }


//    private ResponseEntity<Map<String,Object>> build(HttpStatus status,Object message){
//        return ResponseEntity.status(status).body(
//                Map.of(
//                        "success",false,
//                        "status", status.value(),
//                        "message",message,
//                        "data", List.of()
//                )
//        );
//
//    }

    private ResponseEntity<Map<String,Object>> build(HttpStatus status,Object message){
        Map<String, Object> body= new LinkedHashMap<>();
        body.put("success",false);
        body.put("status",status.value());
        body.put("message",message);
        body.put("data", new Object());

        return ResponseEntity.status(status).body(body);

    }


}
