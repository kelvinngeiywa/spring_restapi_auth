package com.kelvin.spring_restapi;

import com.kelvin.spring_restapi.customExceptions.DuplicateEntryException;
import com.kelvin.spring_restapi.entities.dtos.ApiResponseDto;
import com.kelvin.spring_restapi.entities.dtos.ResponseDto;
import jakarta.persistence.EntityNotFoundException;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.dao.DataIntegrityViolationException;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.security.access.AccessDeniedException;
import org.springframework.security.core.AuthenticationException;
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

    @ExceptionHandler(EntityNotFoundException.class)
    public ResponseEntity<Map<String,Object>> handleEntityNotFound(EntityNotFoundException ex){
//        String message=ex.getReason() != null ? ex.getReason() : "Request failed";
        return build(HttpStatus.NOT_FOUND,ex.getMessage());
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

    @ExceptionHandler(AuthenticationException.class)
    public ResponseEntity<Map<String, Object>> handleAuthentication(AuthenticationException ex) {
        return build(HttpStatus.UNAUTHORIZED, "Invalid email or password");
    }

    @ExceptionHandler(AccessDeniedException.class)
    public ResponseEntity<Map<String, Object>> handleDenied(AccessDeniedException ex) {
        return build(HttpStatus.FORBIDDEN, "You do not have permission to access this resource");
    }

    @ExceptionHandler(Exception.class)
    public ResponseEntity<ResponseDto<Object>> handleAll(Exception ex) {
        log.error("Unhandled exception", ex);
        return ResponseEntity.status(500)
                .body(new ResponseDto<>(500, "Internal server error", null));
    }

    @ExceptionHandler(DataIntegrityViolationException.class)
    public ResponseEntity<ApiResponseDto<Void>> handleDataIntegrity(DataIntegrityViolationException ex) {
        ApiResponseDto<Void> body = new ApiResponseDto<>(
                HttpStatus.CONFLICT.value(),
                "A record with the same details already exists",
                null
        );
        return ResponseEntity.status(HttpStatus.CONFLICT).body(body);
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
