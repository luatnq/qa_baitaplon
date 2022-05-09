package com.example.qa.repository;

import com.example.qa.data.entity.Request;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.query.Param;
import org.springframework.stereotype.Repository;

import javax.persistence.criteria.CriteriaBuilder;
import java.util.Date;
import java.util.List;

@Repository
public interface RequestRepository extends JpaRepository<Request, Integer> {

    @Query(value = "select req from Request req " +
            " where (req.status = 0 ) " +
            " and (req.expireDate >= :expire_date ) " +
            "order by req.expireDate desc ")
    List<Request> getRequests(@Param("expire_date") Date expireDate);
}
