package com.example.qa.controller;

import com.example.qa.dto.StudyClassDTO;
import com.example.qa.service.SubjectService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import java.util.List;

@RestController
@RequestMapping("api/v1/subjects")
public class SubjectController {

    private final SubjectService subjectService;

    @Autowired
    public SubjectController(SubjectService subjectService) {
        this.subjectService = subjectService;
    }

    @GetMapping("/{subject_id}")
    public ResponseEntity<List<StudyClassDTO>> getStudyClassBySubject(@PathVariable("subject_id") int id) {
        return new ResponseEntity<>(subjectService.getStudyClassBySubject(id), HttpStatus.OK);
    }
}
