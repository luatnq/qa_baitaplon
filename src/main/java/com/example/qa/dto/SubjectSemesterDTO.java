package com.example.qa.dto;

import com.example.qa.data.entity.Semester;
import com.example.qa.data.entity.Subject;
import com.fasterxml.jackson.annotation.JsonProperty;
import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Data;
import lombok.NoArgsConstructor;

import javax.persistence.JoinColumn;
import javax.persistence.ManyToOne;

@Data
@Builder
@NoArgsConstructor
@AllArgsConstructor
public class SubjectSemesterDTO {

    @JsonProperty("id")
    private int id;


    @JsonProperty("semester")
    private SemesterDTO semester;

    @JsonProperty("subject")
    private SubjectDTO subject;
}
