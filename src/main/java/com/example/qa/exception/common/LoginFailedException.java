package com.example.qa.exception.common;

public class LoginFailedException extends CommonException {
    public LoginFailedException(int errorCode, String errorMessage) {
        super(errorCode, errorMessage);
    }
}
