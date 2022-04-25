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
public class TranscriptItemDTO {

    @JsonProperty("id")
    private int id;

    @JsonProperty("subject_point")
    private SubjectPointDTO subjectPoint;

    @JsonProperty("point")
    private Float point;
}
