package com.example.qa.controller;

import com.example.qa.dto.StudyClassDTO;
import com.example.qa.dto.SubjectDTO;
import com.example.qa.service.SubjectService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

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
    public ResponseEntity<List<StudyClassDTO>> getStudyClassBySubject(@PathVariable("subject_id") int subjectId,
                                                                      @RequestParam("semester_id") int semesterId) {
        return new ResponseEntity<>(subjectService.getStudyClassBySubject(subjectId, semesterId), HttpStatus.OK);
    }


    @GetMapping
    public ResponseEntity<List<SubjectDTO>> getSubjectsByTeacher(@RequestParam(name = "username") String username){
        return new ResponseEntity<>(subjectService.getSubjects(username), HttpStatus.OK);
    }
}
