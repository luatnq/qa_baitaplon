package com.example.qa.service.impl;

import com.example.qa.data.entity.*;
import com.example.qa.dto.LogDTO;
import com.example.qa.repository.LogRepository;
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
    private final LogRepository logRepository;

    private final String UPDATE_ACTION = "update";
    private final String CREATE_ACTION = "create";
    private final String DELETE_ACTION = "delete";

    @Transactional
    public void createTranscriptHis(TranscriptLine transcriptLine, String username, String action, LogHistory log) {
        User user = userRepository.findByUsername(username);
        TranscriptLineHis transcriptLineHis = new TranscriptLineHis(transcriptLine, user,
                action.equals(CREATE_ACTION) ? CREATE_ACTION : action.equals(UPDATE_ACTION) ? UPDATE_ACTION : DELETE_ACTION, log);

        List<TranscriptItemHis> transcriptItemHis = new ArrayList<>();
        for (TranscriptItem item : transcriptLine.getTranscriptItems()) {
            transcriptItemHis.add(new TranscriptItemHis(item, transcriptLineHis));
        }
        transcriptHisRepository.save(transcriptLineHis);
        transcriptItemHisRepository.saveAll(transcriptItemHis);
    }

    public List<LogDTO> getTranscriptLineHis() {
        List<LogHistory> logs = logRepository.getLogs();
        List<LogDTO> logDTOs = new ArrayList<>();

        for (LogHistory log : logs){
            List<TranscriptItemHis> transcriptItemHis = new ArrayList<>(transcriptItemHisRepository.getTranscriptItemHisByLogHistory(log));
            if (transcriptItemHis.isEmpty()){
                continue;
            }
            String className = transcriptItemHis.get(0).getTranscriptLineHis().getStudyClass().getClassName();
            String subjectName = transcriptItemHis.get(0).getSubjectPoint().getSubject().getName();
            logDTOs.add(new LogDTO(log.getId(), log.getAction(), log.getLastUpdatedDate().toString(), log.getUser().getUsername(), subjectName, className));
        }
        return logDTOs;
    }
}
