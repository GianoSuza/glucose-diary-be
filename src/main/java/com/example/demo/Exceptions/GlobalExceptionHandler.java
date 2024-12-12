package com.example.demo.Exceptions;

import com.example.demo.DTO.ResponseData;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.ExceptionHandler;
import org.springframework.web.bind.annotation.RestControllerAdvice;

@RestControllerAdvice
public class GlobalExceptionHandler {

    @ExceptionHandler(IllegalArgumentException.class)
    public ResponseEntity<ResponseData<String>> handleIllegalArgumentException(IllegalArgumentException ex) {
        ResponseData<String> responseData = new ResponseData<>();
        responseData.setStatus(false);
        responseData.getMessage().add(ex.getMessage());
        responseData.setPayload(null);
        return ResponseEntity.badRequest().body(responseData);
    }
}