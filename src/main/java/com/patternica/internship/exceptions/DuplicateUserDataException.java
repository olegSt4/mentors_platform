package com.patternica.internship.exceptions;

public class DuplicateUserDataException extends RuntimeException {
    public DuplicateUserDataException(String message, Throwable cause) {
        super(message, cause);
    }
}
