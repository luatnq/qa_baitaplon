package com.example.qa.data.entity;


import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

import javax.persistence.*;
import java.io.Serializable;
import java.util.Date;
import java.util.Set;

@Entity
@Table(name = "students")
@Data
@AllArgsConstructor
@NoArgsConstructor
public class Student implements Serializable {
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private int id;

    @Column(name = "full_name")
    private String fullName;

    @Column(name = "student_code")
    private String studentCode;

    @Column(name = "dob")
    private Date dob;

    @ManyToMany(fetch = FetchType.EAGER)
    @JoinTable(name = "student_transcript",
            joinColumns = @JoinColumn(name = "student_id", referencedColumnName = "id"),
            inverseJoinColumns = @JoinColumn(name = "transcript_id", referencedColumnName = "id"))
    private Set<TranscriptLine> transcriptLine;

    @ManyToMany(fetch = FetchType.EAGER)
    @JoinTable(name = "student_transcript_his",
            joinColumns = @JoinColumn(name = "student_id", referencedColumnName = "id"),
            inverseJoinColumns = @JoinColumn(name = "transcript_his_id", referencedColumnName = "id"))
    private Set<TranscriptLineHis> transcriptLineHis;


}
