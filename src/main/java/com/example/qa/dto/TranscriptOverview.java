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
}
