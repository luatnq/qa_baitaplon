package com.example.qa.dto;

import com.fasterxml.jackson.annotation.JsonProperty;
import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

import java.util.List;

@Data
@AllArgsConstructor
@NoArgsConstructor
public class TranscriptOverview {

    @JsonProperty("transcript_lines")
    private List<TranscriptLineDTO> transcriptLineDTOs;

    @JsonProperty("subject_points")
    private List<SubjectPointDTO> subjectPointDTOs;

    @JsonProperty("statistic_student_pass")
    private int statisticStudentPass;

    @JsonProperty("statistic_student_fail")
    private int statisticStudentFail;

    @JsonProperty("statistic_student_eligible")
    private int statisticStudentEligible;

    @JsonProperty("statistic_student_non_eligible")
    private int statisticStudentNonEligible;


}
