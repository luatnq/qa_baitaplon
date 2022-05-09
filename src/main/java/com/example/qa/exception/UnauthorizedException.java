package com.example.qa.exception;

import com.example.qa.exception.common.AuthError;
import com.example.qa.exception.common.AuthException;

public class UnauthorizedException extends AuthException {
    public UnauthorizedException(){
        super(AuthError.USER_UNAUTHORIZED);
    }
}
