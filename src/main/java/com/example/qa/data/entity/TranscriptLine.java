package com.example.qa.data.entity;


import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;
import org.hibernate.annotations.Proxy;

import javax.persistence.*;
import java.util.List;

@Entity
@Table(name = "transcripts")
@Data
@AllArgsConstructor
@NoArgsConstructor
@Proxy(lazy=false)
public class TranscriptLine extends BaseEntity{

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private int id;

    @Column(name = "status")
    private boolean status;

    @ManyToOne
    @JoinColumn(name = "study_class_id", referencedColumnName = "id")
    private StudyClass studyClass;

    @ManyToOne
    @JoinColumn(name = "student_id", referencedColumnName = "id")
    private Student student;

//    @ElementCollection(fetch = FetchType.LAZY)
    @OneToMany(mappedBy = "transcriptLine")
    private List<TranscriptItem> transcriptItems;


}
