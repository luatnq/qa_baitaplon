package com.example.qa.repository;

import com.example.qa.data.entity.Teacher;
import com.example.qa.data.entity.User;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.query.Param;
import org.springframework.stereotype.Repository;

import java.util.Optional;

@Repository
public interface UserRepository extends JpaRepository<User, Integer> {

//    User findByUsername(String username);


    User findByUsername(String username);

    @Query(value = "select u from User u where u.username = :username ")
    Optional<User> findByUsername2(@Param("username") String username);
}
