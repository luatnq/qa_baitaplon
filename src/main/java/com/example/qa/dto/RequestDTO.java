package com.example.qa.dto;

import com.fasterxml.jackson.annotation.JsonProperty;
import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;
import org.springframework.format.annotation.DateTimeFormat;

import javax.persistence.Column;
import java.util.Date;

@Data
@AllArgsConstructor
@NoArgsConstructor
public class RequestDTO {

    private int id;

    @JsonProperty("username_req")
    private String usernameReq;

    @JsonProperty("status")
    private int status;

    @JsonProperty("expire_date")
    @DateTimeFormat(pattern="yyyy.MM.dd hh:mm:ss")
    private String expireDate;

    @JsonProperty("subject_name")
    private String subjectName;

    @JsonProperty("class_name")
    private String className;

    @JsonProperty("subject_id")
    private int subjectId;

    @JsonProperty("class_id")
    private int classId;

    @JsonProperty("username_approve")
    private String usernameApprove;
}
