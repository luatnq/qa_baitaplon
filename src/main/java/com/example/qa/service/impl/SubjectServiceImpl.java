package com.example.qa.service.impl;

import com.example.qa.converter.MappingHelper;
import com.example.qa.data.entity.*;
import com.example.qa.dto.StudyClassDTO;
import com.example.qa.dto.SubjectDTO;
import com.example.qa.repository.StudyClassRepository;
import com.example.qa.repository.SubjectRepository;
import com.example.qa.repository.SubjectSemesterRepository;
import com.example.qa.repository.UserRepository;
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

//    @Autowired
//    public SubjectServiceImpl(MappingHelper mappingHelper, SubjectRepository subjectRepository) {
//        this.mappingHelper = mappingHelper;
//        this.subjectRepository = subjectRepository;
//    }

    @Override
    public List<StudyClassDTO> getStudyClassBySubject(int subjectId, int semesterId) {
//        Subject subject = subjectRepository.findById(subjectId).orElse(null);
//        if (subject == null) return null;
//        if (subject.getSubjectPoints().isEmpty()){
//            return new ArrayList<>();
//        }
//        List<TranscriptItem> transcriptItems = subject.getSubjectPoints().get(0).getTranscriptItems();
//        List<StudyClass> studyClasses = transcriptItems.stream()
//                .map(item -> {
//                    return item.getTranscriptLine().getStudyClass();
//                })
//                .collect(Collectors.toList());
        SubjectSemester subjectSemester = subjectSemesterRepository.getSubjectSemesterBySemesterIdAndSubjectId(semesterId, subjectId);

        return mappingHelper.mapList(studyClassRepository.getStudyClassBySubjectSemester(subjectSemester), StudyClassDTO.class);
    }

    @Override
    public List<SubjectDTO> getSubjects(String username){
        Teacher teacher = userRepository.findByUsername(username);
        if (Objects.isNull(teacher) || teacher.getSubjects().isEmpty()) return new ArrayList<>();
        return  mappingHelper.mapList(new ArrayList<>(teacher.getSubjects()), SubjectDTO.class);
    }
}
