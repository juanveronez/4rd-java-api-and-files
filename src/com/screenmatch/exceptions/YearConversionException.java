package com.screenmatch.exceptions;

public class YearConversionException extends RuntimeException {
    private String message;

    public YearConversionException(String message) {
        this.message = message;
    }

    @Override
    public String getMessage() {
        return message;
    }
}
