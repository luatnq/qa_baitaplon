package com.example.qa.dto;

import com.fasterxml.jackson.annotation.JsonProperty;
import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

@Data
@AllArgsConstructor
@NoArgsConstructor
public class LogDTO {
    private int id;

    @JsonProperty("action")
    private String action;

    @JsonProperty("last_updated_date")
    private String lastUpdatedDate;

    @JsonProperty("username")
    private String username;

    @JsonProperty("subject_name")
    private String subjectName;

    @JsonProperty("class_name")
    private String className;

}
