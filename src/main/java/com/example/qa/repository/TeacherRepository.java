package com.example.qa.repository;

import com.example.qa.data.entity.Teacher;
import com.example.qa.data.entity.User;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

@Repository
public interface TeacherRepository extends JpaRepository<Teacher, Integer> {
    Teacher findByUsername(String username);
}
