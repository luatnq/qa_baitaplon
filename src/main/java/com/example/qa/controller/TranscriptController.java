package com.example.qa.controller;

import com.example.qa.data.entity.TranscriptLine;
import com.example.qa.dto.TranscriptLineDTO;
import com.example.qa.dto.TranscriptOverview;
import com.example.qa.service.TranscriptLineService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@RestController
@RequestMapping("api/v1/transcripts")
public class TranscriptController {

    private final TranscriptLineService transcriptLineService;

    @Autowired
    public TranscriptController(TranscriptLineService transcriptLineService) {
        this.transcriptLineService = transcriptLineService;
    }


    @GetMapping
    public ResponseEntity<TranscriptOverview> getTranscript(@RequestParam(name = "study_class_id") int studyClassId,
                                                            @RequestParam(name = "subject_id") int subjectId) {
        return new ResponseEntity<>(transcriptLineService.getTranscript(studyClassId, subjectId), HttpStatus.OK);
    }

    @PutMapping
    public ResponseEntity<List<TranscriptLineDTO>> updatePoint(@RequestBody List<TranscriptLineDTO> transcriptLines) {
        return new ResponseEntity<>(transcriptLineService.updatePoint(1, transcriptLines), HttpStatus.OK);
    }
}
