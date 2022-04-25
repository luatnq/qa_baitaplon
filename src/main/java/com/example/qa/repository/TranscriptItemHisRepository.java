package com.example.qa.repository;

import com.example.qa.data.entity.LogHistory;
import com.example.qa.data.entity.TranscriptItemHis;
import com.example.qa.data.entity.TranscriptLineHis;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.query.Param;
import org.springframework.stereotype.Repository;

import java.util.List;
import java.util.Set;

@Repository
public interface TranscriptItemHisRepository extends JpaRepository<TranscriptItemHis, Integer> {
//    List<TranscriptItemHis> getTranscriptItemHisByTranscriptLineHis(TranscriptLineHis transcriptLineHis);

    @Query("select item from TranscriptItemHis item " +
            "join item.transcriptLineHis tran " +
            "where tran.logHistory = :log ")
    Set<TranscriptItemHis> getTranscriptItemHisByLogHistory(@Param("log") LogHistory logHistory);
}
