package com.example.qa.data.entity;

import com.example.qa.utils.DateUtils;
import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

import javax.persistence.*;


@Entity
@Table(name = "transcript_item_his")
@Data
@AllArgsConstructor
@NoArgsConstructor
public class TranscriptItemHis extends BaseEntity{

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private int id;

    @ManyToOne
    @JoinColumn(name = "transcript_his_id", referencedColumnName = "id")
    private TranscriptLineHis transcriptLineHis;

    @ManyToOne
    @JoinColumn(name = "subject_point_id", referencedColumnName = "id")
    private SubjectPoint subjectPoint;

    @Column(name = "point")
    private float point;

    public TranscriptItemHis(TranscriptItem transcriptItem, TranscriptLineHis transcriptLineHis){
        this.subjectPoint = transcriptItem.getSubjectPoint();
        this.point = transcriptItem.getPoint();
        this.transcriptLineHis =  transcriptLineHis;
        super.setLastUpdatedDate(DateUtils.now());
    }
}
