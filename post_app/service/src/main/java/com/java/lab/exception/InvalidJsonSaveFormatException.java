package com.java.lab.exception;

public class InvalidJsonSaveFormatException extends RuntimeException {
    public InvalidJsonSaveFormatException() {
    }

    public InvalidJsonSaveFormatException(String message) {
        super(message);
    }

    public InvalidJsonSaveFormatException(String message, Throwable cause) {
        super(message, cause);
    }

    public InvalidJsonSaveFormatException(Throwable cause) {
        super(cause);
    }
}
