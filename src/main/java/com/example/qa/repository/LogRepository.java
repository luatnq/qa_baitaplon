package com.example.qa.repository;

import com.example.qa.data.entity.LogHistory;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;
import org.springframework.stereotype.Repository;

import java.util.List;

@Repository
public interface LogRepository extends JpaRepository<LogHistory, Integer> {
    @Query("select log from LogHistory log order by log.lastUpdatedDate desc ")
    List<LogHistory> getLogs();
}