package com.taskflow.backend.exceptions;

import com.taskflow.backend.task.TaskNotFoundException;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.MethodArgumentNotValidException;
import org.springframework.web.bind.annotation.ExceptionHandler;
import org.springframework.web.bind.annotation.RestControllerAdvice;

import java.time.LocalDateTime;
import java.util.HashMap;
import java.util.Map;

@RestControllerAdvice
public class GlobalExceptionHandler extends RuntimeException {

@ExceptionHandler(TaskNotFoundException.class)
   public ResponseEntity<ErrorResponse> handlerTaskNotFoundException(
           TaskNotFoundException exception){

       ErrorResponse error = new ErrorResponse(
               HttpStatus.NOT_FOUND.value(),
               HttpStatus.NOT_FOUND.getReasonPhrase(),
               exception.getMessage(),
               LocalDateTime.now()
       );

       return ResponseEntity
               .status(HttpStatus.NOT_FOUND)
               .body(error);
   }

    @ExceptionHandler(MethodArgumentNotValidException.class)
    public ResponseEntity<Map<String, Object>> handleValidationErrors(
            MethodArgumentNotValidException exception) {

        Map<String, String> fieldErrors = new HashMap<>();

        exception.getBindingResult()
                .getFieldErrors()
                .forEach(error ->
                        fieldErrors.put(error.getField(), error.getDefaultMessage())
                );

        Map<String, Object> response = new HashMap<>();

        response.put("status", HttpStatus.BAD_REQUEST.value());
        response.put("error", "Validation Failed");
        response.put("message", "Invalid request data");
        response.put("fieldErrors", fieldErrors);
        response.put("timestamp", LocalDateTime.now());

        return ResponseEntity
                .status(HttpStatus.BAD_REQUEST)
                .body(response);
    }






}
