package com.example.task.exception;

public class CustomException  extends RuntimeException{

    private Integer errorCode;

    public CustomException(String message, Integer errorCode) {
        super(message);
        this.errorCode = errorCode;
    }

    public static void throwException(String message , Integer errorCode)  {
        throw new CustomException(message, errorCode);
    }

    public static CustomException customException(String message, Integer errorCode) {
        return new CustomException(message, errorCode);
    }

    public CustomException(String message) {
        super(message);
    }

    public Integer getErrorCode() {
        return errorCode;
    }

    public String getMessage() {
        return super.getMessage();
    }
}
