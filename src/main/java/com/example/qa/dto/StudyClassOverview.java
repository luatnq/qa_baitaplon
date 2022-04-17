package com.example.qa.dto;

import com.fasterxml.jackson.annotation.JsonProperty;
import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

import java.util.List;

@Data
@AllArgsConstructor
@NoArgsConstructor
public class StudyClassOverview {
    @JsonProperty("study_class")
    private StudyClassDTO studyClassDTO;

    @JsonProperty("subject_points")
    private List<SubjectPointDTO> subjectPointDTOs;
}
