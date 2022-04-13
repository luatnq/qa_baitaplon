package com.example.qa.data.dto.response;


import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

@Data
@AllArgsConstructor
@NoArgsConstructor
public class TranscriptItemDTO {
    private SubjectDTO subject;

    private float point;
}
