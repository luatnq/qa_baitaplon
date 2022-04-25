package com.example.qa.repository;

import com.example.qa.data.entity.LogHistory;
import com.example.qa.data.entity.TranscriptLineHis;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

import javax.persistence.criteria.CriteriaBuilder;
import java.util.List;

@Repository
public interface TranscriptHisRepository extends JpaRepository<TranscriptLineHis, Integer> {
    List<TranscriptLineHis> getTranscriptLineHisByLogHistory(LogHistory logHistory);
}
