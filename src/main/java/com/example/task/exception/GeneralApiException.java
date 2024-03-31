package com.example.task.exception;


public class GeneralApiException extends CustomException {
    private Integer errorCode;

    public GeneralApiException(String message) {
        super(message);
    }

    public GeneralApiException(String message, Integer errorCode) {
        super(message);
        this.errorCode = errorCode;
    }

    public static GeneralApiException newExceptionServerError(String message) {
        return new GeneralApiException(message, 500);
    }

    public static GeneralApiException newExceptionNotFound(String message) {
        return new GeneralApiException(message, 404);
    }

    public static GeneralApiException newUserNotFoundException(String message) {
        return new GeneralApiException(message);
    }

    public Integer getErrorCode() {
        return errorCode;
    }
}
