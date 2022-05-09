package com.example.qa.exception.common;

public abstract class CommonException extends RuntimeException{

    private final int errorCode;
    private final String errorMessage;

    protected CommonException(int errorCode, String errorMessage) {
        super();
        this.errorCode = errorCode;
        this.errorMessage = errorMessage;
    }

    public int getErrorCode() {
        return errorCode;
    }

    public String getErrorMessage() {
        return errorMessage;
    }
}
