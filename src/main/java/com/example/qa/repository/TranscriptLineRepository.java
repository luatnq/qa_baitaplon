package com.example.qa.repository;

import com.example.qa.data.entity.TranscriptLine;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

import java.util.List;

@Repository
public interface TranscriptLineRepository extends JpaRepository<TranscriptLine, Integer> {

    List<TranscriptLine> findByStudyClassId(int studyClassId);
}
