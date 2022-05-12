package com.example.qa.service;

import com.example.qa.dto.*;

import java.util.List;

public interface TranscriptLineService {

    TranscriptOverview getTranscript(int studyClassId, int subjectId);

    List<TranscriptLineDTO> updatePoint(int studyClassId, List<TranscriptLineDTO> updatedTranscriptLines,
                                        String username, int requestId);

    //    List<TranscriptLine> insertPoint(int studyClassId, List<TranscriptLine> transcriptLines);
    RequestDTO sendRequest(RequestDTO requestDTO);

    BaseResponse approveRequest(String usernameApprove, int id, int status);

    BaseResponse checkRequest(int id);

    BaseResponse getRequests();

    void deleteById(int reqId);
}
