package com.example.qa.service.impl;

import com.example.qa.converter.MappingHelper;
import com.example.qa.data.entity.StudyClass;
import com.example.qa.data.entity.Subject;
import com.example.qa.data.entity.TranscriptItem;
import com.example.qa.dto.StudyClassDTO;
import com.example.qa.repository.SubjectRepository;
import com.example.qa.service.SubjectService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.List;
import java.util.stream.Collectors;

@Service
public class SubjectServiceImpl implements SubjectService {

    final private MappingHelper mappingHelper;
    private final SubjectRepository subjectRepository;

    @Autowired
    public SubjectServiceImpl(MappingHelper mappingHelper, SubjectRepository subjectRepository) {
        this.mappingHelper = mappingHelper;
        this.subjectRepository = subjectRepository;
    }

    @Override
    public List<StudyClassDTO> getStudyClassBySubject(int subjectId) {
        Subject subject = subjectRepository.findById(subjectId).orElse(null);
        if (subject == null) return null;
        List<TranscriptItem> transcriptItems = subject.getSubjectPoints().get(0).getTranscriptItems();
        List<StudyClass> studyClasses = transcriptItems.stream()
                .map(item -> {
                    return item.getTranscriptLine().getStudyClass();
                })
                .collect(Collectors.toList());
        return mappingHelper.mapList(studyClasses, StudyClassDTO.class);
    }
}
