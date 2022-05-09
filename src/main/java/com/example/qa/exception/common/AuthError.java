package com.example.qa.exception.common;

import lombok.Getter;

@Getter
public enum AuthError {
    USER_NOT_FOUND(404, "User not found"),

    USER_BAD_REQUEST(400, "User bad request"),

    USER_UNAUTHORIZED(401, "User unauthorized");

    private final int errorCode;
    private final String errorMessage;

    AuthError(int errorCode, String errorMessage) {
        this.errorCode = errorCode;
        this.errorMessage = errorMessage;
    }

}
