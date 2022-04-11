package com.example.qa.repository;

import com.example.qa.data.entity.TranscriptLineHis;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

import javax.persistence.criteria.CriteriaBuilder;

@Repository
public interface TranscriptHisRepository extends JpaRepository<TranscriptLineHis, Integer> {
}
