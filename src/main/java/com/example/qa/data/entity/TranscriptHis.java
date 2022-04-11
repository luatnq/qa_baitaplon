package com.example.qa.data.entity;

import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

import javax.persistence.*;


@Entity
@Table(name = "transcript_his")
@Data
@AllArgsConstructor
@NoArgsConstructor
public class TranscriptHis extends BaseEntity{
    @Id
    @GeneratedValue(strategy = GenerationType.AUTO)
    private int id;

    @ManyToOne
    @JoinColumn(name = "subject_id", referencedColumnName = "id")
    private Subject subject;

    @OneToOne
    private Transcript transcript;

    @Column(name = "action")
    private String action;

    @OneToOne
    @JoinColumn(name = "user_id")
    private User user;
}
