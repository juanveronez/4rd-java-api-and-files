package com.consultcep.exceptions;

public class BadCepException extends RuntimeException {
    public BadCepException(String message) {
        super(message);
    }
}
