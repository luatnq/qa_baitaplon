package com.example.qa.controller;

import com.example.qa.data.entity.TranscriptLine;
import com.example.qa.dto.*;
import com.example.qa.service.TranscriptHisService;
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

    private final TranscriptHisService transcriptHisService;

    @Autowired
    public TranscriptController(TranscriptLineService transcriptLineService,
                                TranscriptHisService transcriptHisService) {
        this.transcriptLineService = transcriptLineService;
        this.transcriptHisService = transcriptHisService;
    }

    @GetMapping("/log_histories")
    public ResponseEntity<List<LogDTO>> getLogHistories(){
        return ResponseEntity.ok(transcriptHisService.getTranscriptLineHis());
    }

    @GetMapping
    public ResponseEntity<TranscriptOverview> getTranscript(@RequestParam(name = "study_class_id") int studyClassId,
                                                            @RequestParam(name = "subject_id") int subjectId) {
        return new ResponseEntity<>(transcriptLineService.getTranscript(studyClassId, subjectId), HttpStatus.OK);
    }

    @PutMapping
    public ResponseEntity<List<TranscriptLineDTO>> updatePoint(@RequestBody List<TranscriptLineDTO> transcriptLines,
                                                               @RequestHeader(name = "X-username") String username,
                                                               @RequestParam(name = "request_id") int requestId) {
        return new ResponseEntity<>(transcriptLineService.updatePoint(1, transcriptLines, username, requestId), HttpStatus.OK);
    }

    @PostMapping("/send-request")
    public ResponseEntity<RequestDTO> sendRequest(@RequestBody RequestDTO requestDTO){
        return new ResponseEntity<>(transcriptLineService.sendRequest(requestDTO), HttpStatus.CREATED);
    }

    @PostMapping("/request/{id}")
    public ResponseEntity<BaseResponse> approveRequest(@PathVariable(name = "id") int id,
                                                       @RequestParam(name = "username_approve") String usernameApprove,
                                                       @RequestParam(name = "status") int status){
        return new ResponseEntity<>(transcriptLineService.approveRequest(usernameApprove, id, status), HttpStatus.OK);
    }

    @GetMapping("/request/{id}")
    public ResponseEntity<BaseResponse> checkRequest(@PathVariable(name = "id") int id) {
        return new ResponseEntity<>(transcriptLineService.checkRequest(id), HttpStatus.OK);
    }

    @GetMapping("/requests")
    public ResponseEntity<BaseResponse> getRequests(){
        return new ResponseEntity<>(transcriptLineService.getRequests(), HttpStatus.OK);
    }
}
