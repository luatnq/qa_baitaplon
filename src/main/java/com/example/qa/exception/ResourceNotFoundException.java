package com.example.qa.exception;

import com.example.qa.exception.common.QAError;
import com.example.qa.exception.common.QAException;

public class ResourceNotFoundException extends QAException {

    public ResourceNotFoundException() {
        super(QAError.USER_NOT_FOUND);
    }

}
