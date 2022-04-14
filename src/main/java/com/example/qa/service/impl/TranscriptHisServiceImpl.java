package com.example.qa.service.impl;

import com.example.qa.data.entity.*;
import com.example.qa.repository.TranscriptHisRepository;
import com.example.qa.repository.TranscriptItemHisRepository;
import com.example.qa.repository.UserRepository;
import com.example.qa.service.TranscriptHisService;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import java.util.ArrayList;
import java.util.List;


@Service
@RequiredArgsConstructor
public class TranscriptHisServiceImpl implements TranscriptHisService {
    private final TranscriptHisRepository transcriptHisRepository;
    private final TranscriptItemHisRepository transcriptItemHisRepository;
    private final UserRepository userRepository;

    private final String UPDATE_ACTION = "update";
    private final String CREATE_ACTION = "create";
    private final String DELETE_ACTION = "delete";

    @Transactional
    public void createTranscriptHis(TranscriptLine transcriptLine, String username, List<TranscriptItem> transcriptItems, String action) {
        User user = userRepository.findByUsername(username);
        TranscriptLineHis transcriptLineHis = new TranscriptLineHis(transcriptLine, user,
                action.equals(CREATE_ACTION) ? CREATE_ACTION : action.equals(UPDATE_ACTION) ? UPDATE_ACTION : DELETE_ACTION);

        List<TranscriptItemHis> transcriptItemHis = new ArrayList<>();
        for (TranscriptItem item : transcriptItems) {
            transcriptItemHis.add(new TranscriptItemHis(item, transcriptLineHis));
        }

        transcriptHisRepository.save(transcriptLineHis);
        transcriptItemHisRepository.saveAll(transcriptItemHis);
    }
}
