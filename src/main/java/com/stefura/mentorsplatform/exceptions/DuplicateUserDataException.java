package com.stefura.mentorsplatform.exceptions;

public class DuplicateUserDataException extends RuntimeException {
    public DuplicateUserDataException(String message, Throwable cause) {
        super(message, cause);
    }
}
