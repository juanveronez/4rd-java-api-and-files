package com.screenmatch.modules.module3.exceptions;

public class BadFormatPasswordException extends RuntimeException {
    public BadFormatPasswordException(String mensagem) {
        super(mensagem);
    }
}
