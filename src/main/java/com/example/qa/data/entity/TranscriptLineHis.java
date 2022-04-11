package com.example.qa.data.entity;

import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

import javax.persistence.*;
import java.time.Instant;


@Entity
@Table(name = "transcript_his")
@Data
@AllArgsConstructor
@NoArgsConstructor
public class TranscriptLineHis extends BaseEntity{
    @Id
    @GeneratedValue(strategy = GenerationType.AUTO)
    private int id;

    @Column(name = "status")
    private boolean status;

    @ManyToOne
    @JoinColumn(name = "study_class_id", referencedColumnName = "id")
    private StudyClass studyClass;

    @OneToOne
    private TranscriptLine transcriptLine;

    @Column(name = "action")
    private String action;

    @OneToOne
    @JoinColumn(name = "user_id")
    private User user;

    public TranscriptLineHis(TranscriptLine transcriptLine, User user, String action){
        this.action = action;
        this.status = transcriptLine.isStatus();
        this.studyClass = transcriptLine.getStudyClass();
        this.user = user;
        this.transcriptLine = transcriptLine;
        super.setLastUpdatedDate(Instant.now());
    }
}
