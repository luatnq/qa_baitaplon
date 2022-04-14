package com.example.qa.service.impl;

import com.example.qa.converter.MappingHelper;
import com.example.qa.dto.TranscriptLineDTO;
import com.example.qa.repository.*;
import com.example.qa.service.TranscriptLineService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.List;

@Service
public class TranscriptLineServiceImpl implements TranscriptLineService {

    final private MappingHelper mappingHelper;
    private final TranscriptLineRepository transcriptLineRepository;


    @Autowired
    public TranscriptLineServiceImpl(MappingHelper mappingHelper, TranscriptLineRepository transcriptLineRepository) {
        this.mappingHelper = mappingHelper;
        this.transcriptLineRepository = transcriptLineRepository;
    }

    @Override
    public List<TranscriptLineDTO> getTranscript(int studyClassId) {
        return mappingHelper.mapList(transcriptLineRepository.findByStudyClassId(studyClassId), TranscriptLineDTO.class);

    }

//    @Override
//    public List<TranscriptLine> insertPoint(int studyClassId, List<TranscriptLine> updatedTranscriptLines) {
//        List<TranscriptLine> transcriptLines = getTranscript(studyClassId);
//        int updateLine = 0;
//        for (TranscriptLine line: transcriptLines) {
//            transcriptItemRepository.deleteById(line.getId());
//            List<TranscriptItem> updatedItem = updatedTranscriptLines.get(updateLine).getTranscriptItems()
//                    .stream().map(transcriptItem -> transcriptItemRepository.save(transcriptItem))
//                    .collect(Collectors.toList());
//            line.setTranscriptItems(updatedItem);
//            updateLine++;
//        }
//        return transcriptLines.stream().map(line -> transcriptLineRepository.save(line))
//                .collect(Collectors.toList());
//    }

}
