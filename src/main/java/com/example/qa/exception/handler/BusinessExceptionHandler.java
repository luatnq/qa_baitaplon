package com.example.qa.exception.handler;

import com.example.qa.dto.BaseResponse;
import com.example.qa.exception.common.QAException;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.ExceptionHandler;
import org.springframework.web.bind.annotation.RestControllerAdvice;
import org.springframework.web.servlet.mvc.method.annotation.ResponseEntityExceptionHandler;

import java.time.LocalDateTime;

@RestControllerAdvice
public class BusinessExceptionHandler extends ResponseEntityExceptionHandler {

    @ExceptionHandler(QAException.class)
    public ResponseEntity<BaseResponse> handleReviewException(QAException exception) {
        return new ResponseEntity(BaseResponse.builder()
                .status(exception.getQAError().getErrorCode())
                .timestamp(LocalDateTime.now())
                .message(exception.getQAError().getErrorMessage()).build(),
                HttpStatus.valueOf(exception.getQAError().getErrorCode()));
    }


}
