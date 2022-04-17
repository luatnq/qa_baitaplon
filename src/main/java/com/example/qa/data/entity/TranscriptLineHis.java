package com.example.qa.data.entity;

import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

import javax.persistence.*;
import java.time.Instant;
import java.time.ZoneId;
import java.time.ZonedDateTime;


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

    @ManyToOne
    @JoinColumn(name = "student_id", referencedColumnName = "id")
    private Student student;

    public TranscriptLineHis(TranscriptLine transcriptLine, User user, String action){
        this.action = action;
        this.status = transcriptLine.isStatus();
        this.studyClass = transcriptLine.getStudyClass();
        this.user = user;
        this.student = transcriptLine.getStudent();
        this.transcriptLine = transcriptLine;
        ZoneId asiaVietnamese = ZoneId.of("Asia/Vietnamese");
        super.setLastUpdatedDate(ZonedDateTime.ofInstant(Instant.now(), asiaVietnamese).toInstant());
    }
}
