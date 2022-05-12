package com.example.qa.exception;

import com.example.qa.exception.common.QAError;
import com.example.qa.exception.common.QAException;

public class BadRequestAlertException extends QAException {

    public BadRequestAlertException(){
        super(QAError.USER_BAD_REQUEST);
    }

}
