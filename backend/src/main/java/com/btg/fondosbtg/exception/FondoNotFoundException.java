package com.btg.fondosbtg.exception;

public class FondoNotFoundException extends RuntimeException {

    public FondoNotFoundException(String message) {
        super(message);
    }

    public FondoNotFoundException(String message, Throwable cause) {
        super(message, cause);
    }
}