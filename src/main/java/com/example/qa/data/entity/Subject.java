package com.example.qa.data.entity;

import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

import javax.persistence.*;
import java.util.List;
import java.util.Set;


@Entity
@Table(name = "subjects")
@Data
@AllArgsConstructor
@NoArgsConstructor
public class Subject {
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private int id;

    @Column(name = "name")
    private String name;

    @Column(name = "subject_code")
    private String subjectCode;

    @OneToMany(mappedBy = "subject")
    private List<SubjectPoint> subjectPoints;
}
