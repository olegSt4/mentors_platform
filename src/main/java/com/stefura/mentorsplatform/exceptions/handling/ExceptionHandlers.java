package com.stefura.mentorsplatform.exceptions.handling;

import com.stefura.mentorsplatform.exceptions.*;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.security.core.userdetails.UsernameNotFoundException;
import org.springframework.web.bind.annotation.ControllerAdvice;
import org.springframework.web.bind.annotation.ExceptionHandler;

import java.time.LocalDateTime;

@ControllerAdvice
public class ExceptionHandlers {
    private static final Logger LOGGER = LoggerFactory.getLogger(ExceptionHandler.class);

    @ExceptionHandler(
            value = {EntityNotFoundException.class, UsernameNotFoundException.class})
    public ResponseEntity<ErrorResponse> handleNotFoundExceptions (Exception ex) {
        LOGGER.error(ex.getMessage(), ex);

        ErrorResponse errorResponse = new ErrorResponse();
        errorResponse.setTimestamp(LocalDateTime.now());
        errorResponse.setErrorStatus(HttpStatus.NOT_FOUND.value());
        errorResponse.setErrorName(HttpStatus.NOT_FOUND.toString());
        errorResponse.setInternalError(ex.getClass().getSimpleName());
        errorResponse.setMessage(ex.getMessage());

        return new ResponseEntity<>(errorResponse, HttpStatus.NOT_FOUND);
    }

    @ExceptionHandler(ImageProcessingException.class)
    public ResponseEntity<ErrorResponse> handleImageProcessingExceptions(ImageProcessingException ex) {
        LOGGER.error(ex.getMessage(), ex);

        ErrorResponse errorResponse = new ErrorResponse();
        errorResponse.setTimestamp(LocalDateTime.now());
        errorResponse.setErrorStatus(ex.getErrorStatus().value());
        errorResponse.setErrorName(ex.getErrorStatus().toString());
        errorResponse.setInternalError(ex.getClass().getSimpleName());
        errorResponse.setMessage(ex.getMessage());

        return new ResponseEntity<>(errorResponse, ex.getErrorStatus());
    }

    @ExceptionHandler(IllegalImageTypeException.class)
    public ResponseEntity<ErrorResponse> handleIllegalImageTypeExceptions(IllegalImageTypeException ex) {
        LOGGER.error(ex.getMessage(), ex);

        ErrorResponse errorResponse = new ErrorResponse();
        errorResponse.setTimestamp(LocalDateTime.now());
        errorResponse.setErrorStatus(HttpStatus.BAD_REQUEST.value());
        errorResponse.setErrorName(HttpStatus.BAD_REQUEST.toString());
        errorResponse.setInternalError(ex.getClass().getSimpleName());
        errorResponse.setMessage(ex.getMessage());

        return new ResponseEntity<>(errorResponse, HttpStatus.BAD_REQUEST);
    }

    @ExceptionHandler(DuplicateUserDataException.class)
    public ResponseEntity<ErrorResponse> handleDuplicateUserDataExceptions(DuplicateUserDataException ex) {
        LOGGER.error(ex.getMessage(), ex);

        ErrorResponse errorResponse = new ErrorResponse();
        errorResponse.setTimestamp(LocalDateTime.now());
        errorResponse.setErrorStatus(HttpStatus.BAD_REQUEST.value());
        errorResponse.setErrorName(HttpStatus.BAD_REQUEST.toString());
        errorResponse.setInternalError(ex.getClass().getSimpleName());
        errorResponse.setMessage(ex.getMessage());

        return new ResponseEntity<>(errorResponse, HttpStatus.BAD_REQUEST);
    }
}
