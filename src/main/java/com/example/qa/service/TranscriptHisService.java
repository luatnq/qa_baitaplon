package com.example.qa.service;

import com.example.qa.data.entity.LogHistory;
import com.example.qa.data.entity.TranscriptItem;
import com.example.qa.data.entity.TranscriptLine;
import com.example.qa.dto.LogDTO;

import java.util.List;

public interface TranscriptHisService {
    void createTranscriptHis(TranscriptLine transcriptLine, String username, String action, LogHistory log);

    List<LogDTO> getTranscriptLineHis();
}
