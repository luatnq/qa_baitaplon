package com.example.qa.service;

import com.example.qa.dto.StudyClassDTO;
import com.example.qa.dto.SubjectPointDTO;
import com.example.qa.dto.TranscriptLineDTO;
import com.example.qa.dto.TranscriptOverview;

import java.util.List;

public interface TranscriptLineService {

    TranscriptOverview getTranscript(int studyClassId, int subjectId);

    List<TranscriptLineDTO> updatePoint(int studyClassId, List<TranscriptLineDTO> updatedTranscriptLines, String username);

//    List<TranscriptLine> insertPoint(int studyClassId, List<TranscriptLine> transcriptLines);

}
