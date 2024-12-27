package com.example.flights.exception;

import java.time.LocalDateTime;

import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.MethodArgumentNotValidException;
import org.springframework.web.bind.annotation.ExceptionHandler;
import org.springframework.web.bind.annotation.RestControllerAdvice;
import org.springframework.web.method.annotation.MethodArgumentTypeMismatchException;

import com.example.flights.dto.ApiRes;

import jakarta.validation.ConstraintViolationException;
import org.springframework.web.bind.MissingServletRequestParameterException;

@RestControllerAdvice
public class GlobalExceptionHandler {

    @ExceptionHandler(MethodArgumentNotValidException.class)
    public ResponseEntity<ApiRes<?>> handleValidationExceptions(MethodArgumentNotValidException ex) {
        String errorMessage = ex.getBindingResult()
            .getFieldErrors()
            .stream()
            .map(fieldError -> fieldError.getField() + ": " + fieldError.getDefaultMessage())
            .reduce((msg1, msg2) -> msg1 + ", " + msg2)
            .orElse("Validation failed");
        
        ApiRes<String> apiResponse = new ApiRes<>(HttpStatus.BAD_REQUEST.value(), false, "validation error", errorMessage);
        return ResponseEntity.status(HttpStatus.BAD_REQUEST).body(apiResponse);
    }

    @ExceptionHandler(ConstraintViolationException.class)
    public ResponseEntity<ApiRes<String>> handleConstraintViolationExceptions(ConstraintViolationException ex) {
        ApiRes<String> apiResponse = new ApiRes<>(HttpStatus.BAD_REQUEST.value(), false, "Constraint violation error", ex.getMessage());
        return ResponseEntity.status(HttpStatus.BAD_REQUEST).body(apiResponse);
    }

    @ExceptionHandler(MethodArgumentTypeMismatchException.class)
    public ResponseEntity<ApiRes<String>> handleMethodArgumentTypeMismatch(MethodArgumentTypeMismatchException ex) {
        // Check if the mismatch is related to LocalDateTime
        if (ex.getRequiredType() == LocalDateTime.class) {
            ApiRes<String> apiResponse = new ApiRes<>(HttpStatus.BAD_REQUEST.value(), false, 
                "Date format doesn't match. Please use the correct format: yyyy-MM-dd'T'HH:mm:ss[.SSS]", ex.getMessage());
            return ResponseEntity.status(HttpStatus.BAD_REQUEST).body(apiResponse);
        }

        // For other mismatches, return a general message
        ApiRes<String> apiResponse = new ApiRes<>(HttpStatus.BAD_REQUEST.value(), false, "Invalid input", ex.getMessage());
        return ResponseEntity.status(HttpStatus.BAD_REQUEST).body(apiResponse);
    }

    @ExceptionHandler(MissingServletRequestParameterException.class)
    public ResponseEntity<ApiRes<String>> handleMissingServletRequestParameterException(MissingServletRequestParameterException ex) {
        ApiRes<String> apiResponse = new ApiRes<>(HttpStatus.BAD_REQUEST.value(), false, "Missing request parameter", ex.getMessage());
        return ResponseEntity.status(HttpStatus.BAD_REQUEST).body(apiResponse);
    }
}
