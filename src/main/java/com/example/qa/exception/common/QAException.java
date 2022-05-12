package com.example.qa.exception.common;

import lombok.Getter;

@Getter
public class QAException extends RuntimeException{
    private QAError QAError;

    protected QAException(QAError QAError){
        super();
        this.QAError = QAError;
    }
}
