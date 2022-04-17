package com.example.qa.service;

import com.example.qa.dto.StudyClassDTO;
import com.example.qa.dto.StudyClassOverview;
import com.example.qa.dto.SubjectDTO;

import java.util.List;

public interface SubjectService {
    List<StudyClassDTO> getStudyClassBySubject(int subjectId, int semesterId);
    List<SubjectDTO> getSubjects(String username);
    StudyClassOverview getStudyClassInformation(int subjectId, int studyClassId);
}
