package com.example.qa.service;

import com.example.qa.data.entity.TranscriptLine;
import com.example.qa.dto.TranscriptLineDTO;

import java.util.List;

public interface TranscriptLineService {

    List<TranscriptLineDTO> getTranscript(int studyClassId);

//    List<TranscriptLine> insertPoint(int studyClassId, List<TranscriptLine> transcriptLines);

}
