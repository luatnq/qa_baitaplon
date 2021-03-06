package com.example.qa.dto;

import com.fasterxml.jackson.annotation.JsonProperty;
import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Data;
import lombok.NoArgsConstructor;

@Data
@Builder
@NoArgsConstructor
@AllArgsConstructor
public class StudyClassDTO {

    @JsonProperty("id")
    private int id;

    @JsonProperty("class_name")
    private String className;

    @JsonProperty("subject_semester")
    private SubjectSemesterDTO subjectSemester;
}
