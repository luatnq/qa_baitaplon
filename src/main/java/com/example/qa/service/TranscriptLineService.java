package com.example.qa.service;

import com.example.qa.dto.StudyClassDTO;
import com.example.qa.dto.SubjectPointDTO;
import com.example.qa.dto.TranscriptLineDTO;

import java.util.List;

public interface TranscriptLineService {

    List<TranscriptLineDTO> getTranscript(int studyClassId);

//    List<TranscriptLine> insertPoint(int studyClassId, List<TranscriptLine> transcriptLines);

}
