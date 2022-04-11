package com.example.qa.repository;

import com.example.qa.data.entity.TranscriptItemHis;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

@Repository
public interface TranscriptItemHisRepository extends JpaRepository<TranscriptItemHis, Integer> {
}
