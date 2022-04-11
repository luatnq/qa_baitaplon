package com.example.qa.service.impl;

import com.example.qa.data.entity.TranscriptLine;
import com.example.qa.data.entity.TranscriptLineHis;
import com.example.qa.repository.TranscriptHisRepository;
import com.example.qa.repository.TranscriptItemHisRepository;
import com.example.qa.service.TranscriptHisService;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Service;


@Service
@RequiredArgsConstructor
public class TranscriptHisServiceImpl implements TranscriptHisService {
    private final TranscriptHisRepository transcriptHisRepository;
    private final TranscriptItemHisRepository transcriptItemHisRepository;

    public void createTranscriptHis(TranscriptLine transcriptLine){
        TranscriptLineHis transcriptLineHis = new TranscriptLineHis();
    }
}
