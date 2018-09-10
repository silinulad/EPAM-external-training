package com.example.intouch.exceptions;

public class DAOException extends Exception {

    private static final long serialVersionUID = -8657929838608034583L;

    public DAOException() {
    }

    public DAOException(String message) {
        super(message);
    }

    public DAOException(Throwable cause) {
        super(cause);
    }

    public DAOException(String message, Throwable cause) {
        super(message, cause);
    }
}
