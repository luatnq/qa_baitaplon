package com.example.qa.repository;

import com.example.qa.data.entity.StudyClass;
import com.example.qa.data.entity.SubjectSemester;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

import java.util.List;

@Repository
public interface StudyClassRepository extends JpaRepository<StudyClass, Integer> {

    List<StudyClass> getStudyClassBySubjectSemester(SubjectSemester subjectSemester);
}
