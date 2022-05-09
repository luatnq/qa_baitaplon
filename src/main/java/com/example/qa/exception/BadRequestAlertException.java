package com.example.qa.exception;

import com.example.qa.exception.common.AuthError;
import com.example.qa.exception.common.AuthException;

public class BadRequestAlertException extends AuthException {

    public BadRequestAlertException(){
        super(AuthError.USER_BAD_REQUEST);
    }

}
