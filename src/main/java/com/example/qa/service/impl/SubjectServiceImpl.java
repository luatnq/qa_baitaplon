package com.example.qa.service.impl;

import com.example.qa.converter.MappingHelper;
import com.example.qa.data.entity.*;
import com.example.qa.dto.StudyClassDTO;
import com.example.qa.dto.StudyClassOverview;
import com.example.qa.dto.SubjectDTO;
import com.example.qa.dto.SubjectPointDTO;
import com.example.qa.repository.*;
import com.example.qa.service.SubjectService;
import lombok.RequiredArgsConstructor;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.ArrayList;
import java.util.List;
import java.util.Objects;
import java.util.stream.Collectors;

@Service
@RequiredArgsConstructor
public class SubjectServiceImpl implements SubjectService {

    final private MappingHelper mappingHelper;
    private final SubjectRepository subjectRepository;
    private final UserRepository userRepository;
    private final StudyClassRepository studyClassRepository;
    private final SubjectSemesterRepository subjectSemesterRepository;
    private final SubjectPointRepository subjectPointRepository;

    @Override
    public List<StudyClassDTO> getStudyClassBySubject(int subjectId, int semesterId) {
        SubjectSemester subjectSemester = subjectSemesterRepository.getSubjectSemesterBySemesterIdAndSubjectId(semesterId, subjectId);
        return mappingHelper.mapList(studyClassRepository.getStudyClassBySubjectSemester(subjectSemester), StudyClassDTO.class);
    }

    @Override
    public List<SubjectDTO> getSubjects(String username){
        Teacher teacher = userRepository.findByUsername(username);
        if (Objects.isNull(teacher) || teacher.getSubjects().isEmpty()) return new ArrayList<>();
        return  mappingHelper.mapList(new ArrayList<>(teacher.getSubjects()), SubjectDTO.class);
    }


    public StudyClassOverview getStudyClassInformation(int subjectId, int studyClassId){
        List<SubjectPointDTO> subjectPointDTOs = mappingHelper.mapList(subjectPointRepository.getSubjectPoint(subjectId), SubjectPointDTO.class);

        StudyClassDTO studyClassDTO = mappingHelper.map(studyClassRepository.getById(studyClassId), StudyClassDTO.class);

        return new StudyClassOverview(studyClassDTO, subjectPointDTOs);
    }
}
