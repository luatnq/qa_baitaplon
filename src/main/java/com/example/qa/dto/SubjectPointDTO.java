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
public class SubjectPointDTO {

    @JsonProperty("id")
    private int id;

    @JsonProperty("factor")
    private int factor;

    @JsonProperty("point")
    private PointDTO point;

    @JsonProperty("subject")
    private SubjectDTO subject;
}
