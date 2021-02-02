package com.patternica.internship.exceptions;

public class IllegalImageTypeException extends IllegalArgumentException {
    public IllegalImageTypeException() {
        super();
    }

    public IllegalImageTypeException(String message) {
        super(message);
    }
}
