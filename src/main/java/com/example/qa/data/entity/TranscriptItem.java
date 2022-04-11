package com.example.qa.data.entity;

import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

import javax.persistence.*;


@Entity
@Table(name = "transcript_items")
@Data
@AllArgsConstructor
@NoArgsConstructor
public class TranscriptItem extends BaseEntity{
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private int id;

    @ManyToOne
    @JoinColumn(name = "transcript_id", referencedColumnName = "id")
    private Transcript transcript;

    @ManyToOne
    @JoinColumn(name = "subject_point_id", referencedColumnName = "id")
    private SubjectPoint subjectPoint;

    @Column(name = "point")
    private float point;
}
