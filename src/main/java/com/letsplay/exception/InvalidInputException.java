package com.letsplay.exception;

public class InvalidInputException extends Exception {
    public InvalidInputException (){
        super("this was the original message :");
    }

    public InvalidInputException (Throwable cause) {
        super(cause);
    }

    public InvalidInputException (String message) {
        super(message);
    }
}
