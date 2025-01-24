package com.letsplay.exception;

public class EmptyFieldsException extends RuntimeException {
    public EmptyFieldsException(String message) {
        super(message);
    }
}
