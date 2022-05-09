package com.example.qa.exception.common;

import lombok.Getter;

@Getter
public class AuthException extends RuntimeException{
    private AuthError authError;

    protected AuthException(AuthError authError){
        super();
        this.authError = authError;
    }
}
