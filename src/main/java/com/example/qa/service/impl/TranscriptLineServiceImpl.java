package com.example.qa.service.impl;

import com.example.qa.converter.MappingHelper;
import com.example.qa.data.entity.*;
import com.example.qa.dto.SubjectPointDTO;
import com.example.qa.dto.TranscriptItemDTO;
import com.example.qa.dto.TranscriptLineDTO;
import com.example.qa.dto.TranscriptOverview;
import com.example.qa.repository.*;
import com.example.qa.service.TranscriptHisService;
import com.example.qa.service.TranscriptLineService;
import com.example.qa.utils.DateUtils;
import lombok.RequiredArgsConstructor;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import java.util.LinkedHashMap;
import java.util.List;
import java.util.Map;

@Service
@RequiredArgsConstructor
public class TranscriptLineServiceImpl implements TranscriptLineService {

    final private MappingHelper mappingHelper;
    private final TranscriptLineRepository transcriptLineRepository;
    private final TranscriptItemRepository transcriptItemRepository;
    private final SubjectPointRepository subjectPointRepository;
    private final TranscriptHisService transcriptHisService;
    private final LogRepository logRepository;
    private final UserRepository userRepository;

    private final String UPDATE_ACTION = "update";
    private final String CREATE_ACTION = "create";
    private final String DELETE_ACTION = "delete";

    @Override
    public TranscriptOverview getTranscript(int studyClassId, int subjectId) {
        List<TranscriptLineDTO> transcriptLineDTOs = mappingHelper.mapList(
                transcriptLineRepository.findByStudyClassId(studyClassId), TranscriptLineDTO.class);
        List<Integer> transcriptLineIds = transcriptLineRepository.getIdsByStudyClassId(studyClassId);

        int countStudentFailFinalPoint = this.statisticFailFinalPoint(transcriptLineIds);
        int countStudentNonEligible = this.statisticStudentNonEligible(transcriptLineIds, transcriptLineDTOs);

        List<SubjectPointDTO> subjectPointDTOs = mappingHelper.mapList(subjectPointRepository.getSubjectPoint(subjectId), SubjectPointDTO.class);
        return new TranscriptOverview(handleOutputTranscriptLine(transcriptLineDTOs), subjectPointDTOs,
                transcriptLineDTOs.size() - countStudentFailFinalPoint, countStudentFailFinalPoint,
                transcriptLineDTOs.size() - countStudentNonEligible, countStudentNonEligible);
    }

    @Transactional
    public List<TranscriptLineDTO> updatePoint(int studyClassId, List<TranscriptLineDTO> updatedTranscriptLines, String username) {
        List<TranscriptLine> transcriptLines = transcriptLineRepository.findByStudyClassId(studyClassId);
        int updateLine = 0;
        User user = userRepository.findByUsername(username);
        LogHistory log = new LogHistory(UPDATE_ACTION, user);
        logRepository.save(log);
        for (TranscriptLine line : transcriptLines) {
            this.updateTranscriptItem(updatedTranscriptLines.get(updateLine).getTranscriptItems(), line);
            transcriptHisService.createTranscriptHis(line, username, UPDATE_ACTION, log);
            updateLine++;
        }
        return mappingHelper.mapList(transcriptLines, TranscriptLineDTO.class);
    }

    private void updateTranscriptItem(List<TranscriptItemDTO> transcriptItemDTOs, TranscriptLine transcriptLine) {
        int index = 0;
        for (TranscriptItem item : transcriptLine.getTranscriptItems()) {
            item.setPoint(transcriptItemDTOs.get(index).getPoint());
            item.setLastUpdatedDate(DateUtils.now());
            index++;
        }
        transcriptItemRepository.saveAll(transcriptLine.getTranscriptItems());
    }

    private List<TranscriptLineDTO> handleOutputTranscriptLine(List<TranscriptLineDTO> transcriptLineDTOS) {
        for (TranscriptLineDTO transcriptLineDTO : transcriptLineDTOS) {
            transcriptLineDTO.setStudyClass(null);
            for (TranscriptItemDTO item : transcriptLineDTO.getTranscriptItems()) {
                item.getSubjectPoint().setSubject(null);
            }
        }
        return transcriptLineDTOS;
    }

    private boolean checkSubjectPoint(List<Integer> transcriptLineIds, int pointId) {
        List<TranscriptItem> transcriptItems = transcriptItemRepository.getTranscriptItemsByPointId(transcriptLineIds, pointId);
        if (transcriptItems.size() == transcriptLineIds.size()) {
            return false;
        }
        return true;
    }

    private int statisticFailFinalPoint(List<Integer> transcriptLineIds) {
        List<TranscriptItem> transcriptItems = transcriptItemRepository.getTranscriptItemsByPointFinal(transcriptLineIds, 6);
        int countStudentFail = 0;
        if (this.checkSubjectPoint(transcriptLineIds, 6)) {
            for (TranscriptItem item : transcriptItems) {
                if (item.getPoint().compareTo(0F) == 0 && item.getPoint().compareTo(0F) == -1) {
                    countStudentFail++;
                }
            }
        }
        return countStudentFail;
    }

    private int statisticStudentNonEligible(List<Integer> transcriptLineIds, List<TranscriptLineDTO> transcriptLineDTOs) {
        int countStudentFail = 0;
        for(TranscriptLineDTO lineDTO : transcriptLineDTOs){
            boolean flag = false;
            for (TranscriptItemDTO itemDTO : lineDTO.getTranscriptItems()){
                if (checkSubjectPoint(transcriptLineIds, itemDTO.getSubjectPoint().getPoint().getId())){
                    if(itemDTO.getPoint().compareTo(0F) != 1){
                        countStudentFail ++;
                        break;
                    }
                }
            }
        }
        return countStudentFail;
    }

}
