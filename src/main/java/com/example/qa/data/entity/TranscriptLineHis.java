package com.example.qa.data.entity;

import com.example.qa.utils.DateUtils;
import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;
import org.hibernate.annotations.Proxy;

import javax.persistence.*;
import java.time.Instant;
import java.time.ZoneId;
import java.time.ZonedDateTime;


@Entity
@Table(name = "transcript_his")
@Data
@AllArgsConstructor
@NoArgsConstructor
@Proxy(lazy=false)
public class TranscriptLineHis extends BaseEntity{
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
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

    @ManyToOne
    @JoinColumn(name = "log_id", referencedColumnName = "id")
    private LogHistory logHistory;

    public TranscriptLineHis(TranscriptLine transcriptLine, User user, String action, LogHistory logHistory){
        this.action = action;
        this.status = transcriptLine.isStatus();
        this.studyClass = transcriptLine.getStudyClass();
        this.user = user;
        this.student = transcriptLine.getStudent();
        this.transcriptLine = transcriptLine;
        this.logHistory = logHistory;
        super.setLastUpdatedDate(DateUtils.now());
    }
}
