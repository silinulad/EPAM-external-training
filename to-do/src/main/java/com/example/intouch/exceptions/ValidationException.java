package com.example.intouch.exceptions;

public class ValidationException extends Exception {

    private static final long serialVersionUID = -1557537040565529529L;

    public ValidationException() {
    }

    public ValidationException(String message) {
        super(message);
    }

    public ValidationException(Throwable cause) {
        super(cause);
    }

    public ValidationException(String message, Throwable cause) {
        super(message, cause);
    }
}
