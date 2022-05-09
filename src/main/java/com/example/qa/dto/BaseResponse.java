package com.example.qa.dto;

import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Data;
import lombok.NoArgsConstructor;

import java.time.LocalDate;
import java.time.LocalDateTime;
import java.util.Date;

@Data
@AllArgsConstructor
@NoArgsConstructor
@Builder
public class BaseResponse {
    private int status;

    private String message;

    private Object data;

    private LocalDateTime timestamp;

    public BaseResponse(int status, Object data) {
        this.status = status;
        this.data = data;
    }
}
