package com.example.qa.repository;

import com.example.qa.data.entity.SubjectPoint;
import com.example.qa.data.entity.TranscriptItem;
import com.example.qa.data.entity.TranscriptLine;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.query.Param;
import org.springframework.stereotype.Repository;

import java.util.List;

@Repository
public interface TranscriptItemRepository extends JpaRepository<TranscriptItem, Integer> {
    void deleteAllByTranscriptLineId(Integer lineId);

    @Query("select item from TranscriptItem item " +
            "join item.transcriptLine tran  " +
            "where ((:line_ids) is null or tran.id in (:line_ids) ) " +
            "and (item.subjectPoint.id = :subject_point_id )" +
            "and item.point is null ")
    List<TranscriptItem> getTranscriptItems(@Param("line_ids") List<Integer> transcriptLineIds, @Param("subject_point_id") int subjectPointId);

    @Query("select item from TranscriptItem item " +
            "join item.transcriptLine tran " +
            "join item.subjectPoint sp " +
            "where (coalesce(:line_ids) is null or tran.id in (:line_ids) ) " +
            "and (sp.point.id = :point_id ) ")
    List<TranscriptItem> getTranscriptItemsByPointFinal(@Param("line_ids") List<Integer> transcriptLineIds, @Param("point_id") int pointId);

    @Query("select item from TranscriptItem item " +
            "join item.transcriptLine tran " +
            "join item.subjectPoint sp " +
            "where (coalesce(:line_ids) is null or tran.id in (:line_ids) ) " +
            "and (sp.point.id = :point_id )" +
            "and item.point is null ")
    List<TranscriptItem> getTranscriptItemsByPointId(@Param("line_ids") List<Integer> transcriptLineIds, @Param("point_id") int pointId);

    TranscriptItem getTranscriptItemByTranscriptLine_IdAndSubjectPoint_Id(Integer tranId, Integer subjectPointId);
}
