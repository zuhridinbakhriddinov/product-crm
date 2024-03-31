package com.example.task.exception;

public class RestApiSuccessResponseException extends RuntimeException {
    Integer errorCode;
    public RestApiSuccessResponseException(String message, Integer errorCode) {
        super(message);
        this.errorCode = errorCode;
    }
}
