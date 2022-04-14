package com.example.qa.dto;

import com.fasterxml.jackson.annotation.JsonProperty;
import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Data;
import lombok.NoArgsConstructor;

import java.util.List;

@Data
@Builder
@NoArgsConstructor
@AllArgsConstructor
public class TranscriptLineDTO {

    @JsonProperty("id")
    private int id;

    @JsonProperty("status")
    private boolean status;

    @JsonProperty("study_class")
    private StudyClassDTO studyClass;

    @JsonProperty("student")
    private StudentDTO student;

    @JsonProperty("transcript_item")
    private List<TranscriptItemDTO> transcriptItems;
    
}
