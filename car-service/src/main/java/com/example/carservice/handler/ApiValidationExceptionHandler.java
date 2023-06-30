package com.example.carservice.handler;

import com.example.carservice.model.dto.ApiResponse;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.MethodArgumentNotValidException;
import org.springframework.web.bind.annotation.ControllerAdvice;
import org.springframework.web.bind.annotation.ExceptionHandler;

@ControllerAdvice
public class ApiValidationExceptionHandler {

    @ExceptionHandler(MethodArgumentNotValidException.class)
    public ResponseEntity<ApiResponse> handleException() {
        ApiResponse response = new ApiResponse();
        response.setMessage("Validation error");

        return new ResponseEntity<>(response, HttpStatus.BAD_REQUEST);
    }
}
