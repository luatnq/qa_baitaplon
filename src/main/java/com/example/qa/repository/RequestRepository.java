package com.example.qa.repository;

import com.example.qa.data.entity.Request;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

import javax.persistence.criteria.CriteriaBuilder;

@Repository
public interface RequestRepository extends JpaRepository<Request, Integer> {
}
