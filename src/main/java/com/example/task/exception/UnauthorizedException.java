package com.example.task.exception;

import lombok.Getter;

@Getter
public class UnauthorizedException extends RuntimeException {

    private Integer errorCode;

    public UnauthorizedException(String message, Integer errorCode) {
        super(message);
        this.errorCode = errorCode;
    }

    public UnauthorizedException(String message) {
        super(message);
    }
}
