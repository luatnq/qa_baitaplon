package com.example.qa.dto;

import com.fasterxml.jackson.annotation.JsonProperty;
import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Data;
import lombok.NoArgsConstructor;

import javax.persistence.Column;

@Data
@Builder
@NoArgsConstructor
@AllArgsConstructor
public class SemesterDTO {

    @JsonProperty("id")
    private int id;

    @JsonProperty("from_year")
    private int fromYear;

    @JsonProperty("to_year")
    private int toYear;

    @JsonProperty("semester_num")
    private int semesterNum;
}
