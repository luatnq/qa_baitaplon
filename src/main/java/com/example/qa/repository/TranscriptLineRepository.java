package com.example.qa.repository;

import com.example.qa.data.entity.TranscriptLine;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.query.Param;
import org.springframework.stereotype.Repository;

import java.util.List;

@Repository
public interface TranscriptLineRepository extends JpaRepository<TranscriptLine, Integer> {

    List<TranscriptLine> findByStudyClassId(int studyClassId);

    @Query("select trans.id from TranscriptLine trans where trans.studyClass.id = :stu_class_id ")
    List<Integer> getIdsByStudyClassId(@Param("stu_class_id") int studyClassId);
}
