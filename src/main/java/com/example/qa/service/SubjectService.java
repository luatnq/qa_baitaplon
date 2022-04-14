package com.example.qa.service;

import com.example.qa.dto.StudyClassDTO;

import java.util.List;

public interface SubjectService {

    List<StudyClassDTO> getStudyClassBySubject(int subjectId);
}
