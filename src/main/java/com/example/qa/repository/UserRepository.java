package com.example.qa.repository;

import com.example.qa.data.entity.Teacher;
import com.example.qa.data.entity.User;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

@Repository
public interface UserRepository extends JpaRepository<User, Integer> {

//    User findByUsername(String username);

    Teacher findByUsername(String username);
}
