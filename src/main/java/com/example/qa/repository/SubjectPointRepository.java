package com.example.qa.repository;

import com.example.qa.data.entity.SubjectPoint;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

import java.util.List;

@Repository
public interface SubjectPointRepository extends JpaRepository<SubjectPoint, Integer> {

}
