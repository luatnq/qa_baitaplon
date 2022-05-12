package com.example.qa.exception;

import com.example.qa.exception.common.QAError;
import com.example.qa.exception.common.QAException;

public class UnauthorizedException extends QAException {
    public UnauthorizedException(){
        super(QAError.USER_UNAUTHORIZED);
    }
}
