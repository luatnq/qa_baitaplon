package com.example.qa.repository;

import com.example.qa.data.entity.SubjectPoint;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.query.Param;
import org.springframework.stereotype.Repository;

import java.util.List;

@Repository
public interface SubjectPointRepository extends JpaRepository<SubjectPoint, Integer> {
    @Query("select sp from SubjectPoint sp where sp.subject.id = :subject_id ")
    List<SubjectPoint> getSubjectPoint(@Param("subject_id") int subjectId);
}
