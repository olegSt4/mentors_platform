package com.stefura.mentorsplatform.exceptions;

import org.springframework.http.HttpStatus;

public class ImageProcessingException extends RuntimeException {
    private HttpStatus errorStatus;

    public ImageProcessingException(HttpStatus errorStatus) {
        this.errorStatus = errorStatus;
    }

    public ImageProcessingException(String message, HttpStatus errorStatus) {
        super(message);
        this.errorStatus = errorStatus;
    }

    public ImageProcessingException(String message, Throwable cause, HttpStatus errorStatus) {
        super(message, cause);
        this.errorStatus = errorStatus;
    }

    public HttpStatus getErrorStatus() {
        return errorStatus;
    }
}
