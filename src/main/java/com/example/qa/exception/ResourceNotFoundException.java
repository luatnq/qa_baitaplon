package com.example.qa.exception;

import com.example.qa.exception.common.AuthError;
import com.example.qa.exception.common.AuthException;

public class ResourceNotFoundException extends AuthException {

    public ResourceNotFoundException() {
        super(AuthError.USER_NOT_FOUND);
    }

}
