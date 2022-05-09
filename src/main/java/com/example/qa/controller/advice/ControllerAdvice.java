package com.example.qa.controller.advice;


import com.example.qa.exception.common.CommonException;
import lombok.extern.slf4j.Slf4j;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.ExceptionHandler;
import org.springframework.web.bind.annotation.RestControllerAdvice;

@Slf4j
@RestControllerAdvice
public class ControllerAdvice {

    @ExceptionHandler(value = CommonException.class)
    public ResponseEntity<String> UnauthorizedExceptionHandle(CommonException e) {
        return new ResponseEntity<>(
                e.getErrorMessage(),
                HttpStatus.valueOf(e.getErrorCode())
        );
    }
}
