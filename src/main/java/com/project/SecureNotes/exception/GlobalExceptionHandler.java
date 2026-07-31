package com.project.SecureNotes.exception;


import com.project.SecureNotes.dto.ErrorResponse;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.ExceptionHandler;
import org.springframework.web.bind.annotation.RestControllerAdvice;

import java.time.LocalDateTime;

@RestControllerAdvice
public class GlobalExceptionHandler {

    @ExceptionHandler(EmailAlreadyInUseException.class)
    public ResponseEntity<ErrorResponse> handleEmailAlreadyInUseException(EmailAlreadyInUseException ex) {

        ErrorResponse errorDetails = new ErrorResponse(
                LocalDateTime.now(),
                HttpStatus.CONFLICT.value(),       // 409
                HttpStatus.CONFLICT.getReasonPhrase(), // "Conflict"
                ex.getMessage()
        );

        return new ResponseEntity<>(errorDetails, HttpStatus.CONFLICT);
    }
}


