package ru.faickoff.api.auth.exception;

public class TokenValidationException extends Exception {

    public TokenValidationException(String message) {
        super(message);
    }

    public TokenValidationException(String message, Throwable cause) {
        super(message, cause);
    }
}
