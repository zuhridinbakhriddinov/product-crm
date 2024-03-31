package com.example.task.exception;

public class JwtNotValidException extends GeneralApiException {
    public JwtNotValidException(String message) {
        super(message);
    }
}
