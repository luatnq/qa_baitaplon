package com.example.qa.controller;

import com.example.qa.data.entity.TranscriptLine;
import com.example.qa.dto.TranscriptLineDTO;
import com.example.qa.service.TranscriptLineService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@RestController
@RequestMapping("api/v1/transcript")
public class TranscriptController {

    private final TranscriptLineService transcriptLineService;

    @Autowired
    public TranscriptController(TranscriptLineService transcriptLineService) {
        this.transcriptLineService = transcriptLineService;
    }

    @GetMapping("/{study_class_id}")
    public ResponseEntity<List<TranscriptLineDTO>> getTranscript(@PathVariable("study_class_id") int studyClassId) {
        return new ResponseEntity<>(transcriptLineService.getTranscript(studyClassId), HttpStatus.OK);
    }

//    @PutMapping("/{study_class_id}")
//    public ResponseEntity<List<TranscriptLine>> insertPoint(@RequestBody List<TranscriptLine> transcriptLines,
//                                                            @PathVariable("study_class_id") int id) {
//        return new ResponseEntity<>(transcriptLineService.insertPoint(id,transcriptLines), HttpStatus.CREATED);
//    }
}
