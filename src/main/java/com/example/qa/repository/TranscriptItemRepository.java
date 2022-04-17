package com.example.qa.repository;

import com.example.qa.data.entity.TranscriptItem;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

import java.util.List;

@Repository
public interface TranscriptItemRepository extends JpaRepository<TranscriptItem, Integer> {
    void deleteAllByTranscriptLineId(Integer lineId);
}
