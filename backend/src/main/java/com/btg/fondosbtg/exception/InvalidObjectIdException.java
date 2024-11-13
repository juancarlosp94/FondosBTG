package com.btg.fondosbtg.exception;

public class InvalidObjectIdException extends RuntimeException {

    public InvalidObjectIdException(String message) {
        super(message);
    }

    public InvalidObjectIdException(String message, Throwable cause) {
        super(message, cause);
    }
}
