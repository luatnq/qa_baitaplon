package com.example.qa.repository;

import com.example.qa.data.entity.StudyClass;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

@Repository
public interface StudyClassRepository extends JpaRepository<StudyClass, Integer> {
}
