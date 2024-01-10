package com.pesto.ecommerce.exception;

import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.ControllerAdvice;
import org.springframework.web.bind.annotation.ExceptionHandler;
import org.springframework.web.context.request.WebRequest;

import lombok.Getter;
import lombok.Setter;

@ControllerAdvice
public class GlobalExceptionHandler {

    @ExceptionHandler(BadRequestException.class)
    public ResponseEntity<Object> handleBadRequestException(BadRequestException ex, WebRequest request) {
        // Create a custom error response without the stack trace
        CustomErrorResponse errorResponse = new CustomErrorResponse(
                HttpStatus.BAD_REQUEST.value(),
                ex.getMessage(),
                request.getDescription(false));

        return new ResponseEntity<>(errorResponse, HttpStatus.BAD_REQUEST);
    }

    @Getter
    @Setter
    static class CustomErrorResponse {
        private int status;
        private String message;
        private String path;

        public CustomErrorResponse(int status, String message, String path) {
            this.status = status;
            this.message = message;
            this.path = path;
        }

    }
}