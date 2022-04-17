package com.example.qa.service.impl;

import com.example.qa.converter.MappingHelper;
import com.example.qa.data.entity.SubjectPoint;
import com.example.qa.data.entity.TranscriptItem;
import com.example.qa.data.entity.TranscriptLine;
import com.example.qa.dto.SubjectPointDTO;
import com.example.qa.dto.TranscriptItemDTO;
import com.example.qa.dto.TranscriptLineDTO;
import com.example.qa.dto.TranscriptOverview;
import com.example.qa.repository.*;
import com.example.qa.service.TranscriptLineService;
import com.example.qa.utils.DateUtils;
import lombok.RequiredArgsConstructor;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import java.util.List;

@Service
@RequiredArgsConstructor
public class TranscriptLineServiceImpl implements TranscriptLineService {

    final private MappingHelper mappingHelper;
    private final TranscriptLineRepository transcriptLineRepository;
    private final TranscriptItemRepository transcriptItemRepository;

    private final SubjectPointRepository subjectPointRepository;


    @Override
    public TranscriptOverview getTranscript(int studyClassId, int subjectId) {
        List<TranscriptLineDTO> transcriptLineDTOs =  mappingHelper.mapList(
                transcriptLineRepository.findByStudyClassId(studyClassId), TranscriptLineDTO.class);

        List<SubjectPointDTO> subjectPointDTOs = mappingHelper.mapList(subjectPointRepository.getSubjectPoint(subjectId), SubjectPointDTO.class);
        return new TranscriptOverview(transcriptLineDTOs, subjectPointDTOs);
    }

    @Transactional
    public List<TranscriptLineDTO> updatePoint(int studyClassId, List<TranscriptLineDTO> updatedTranscriptLines) {
        List<TranscriptLine> transcriptLines = transcriptLineRepository.findByStudyClassId(studyClassId);
        int updateLine = 0;
        for (TranscriptLine line: transcriptLines) {
            this.updateTranscriptItem(updatedTranscriptLines.get(updateLine).getTranscriptItems(), line);
            updateLine++;
        }

        return mappingHelper.mapList(transcriptLines, TranscriptLineDTO.class);

    }

    private void updateTranscriptItem(List<TranscriptItemDTO> transcriptItemDTOs, TranscriptLine transcriptLine){
        int index = 0;
        for (TranscriptItem item : transcriptLine.getTranscriptItems()){
            item.setPoint(transcriptItemDTOs.get(index).getPoint());
            item.setLastUpdatedDate(DateUtils.now());
            index ++;
        }
        transcriptItemRepository.saveAll(transcriptLine.getTranscriptItems());
    }

}
