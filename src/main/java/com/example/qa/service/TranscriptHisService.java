package com.example.qa.service;

import com.example.qa.data.entity.TranscriptItem;
import com.example.qa.data.entity.TranscriptLine;

import java.util.List;

public interface TranscriptHisService {
    void createTranscriptHis(TranscriptLine transcriptLine, String username, String action);
}
