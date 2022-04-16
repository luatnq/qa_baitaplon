package com.example.qa.data.entity;


import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

import javax.persistence.*;
import java.io.Serializable;

@Entity
@Table(name = "study_class")
@Data
@AllArgsConstructor
@NoArgsConstructor
public class StudyClass implements Serializable {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private int id;

    @Column(name = "class_name")
    private String className;

    @Column(name = "class_room")
    private String classRoom;

    @ManyToOne
    @JoinColumn(name = "subject_semester_id", referencedColumnName = "id")
    private SubjectSemester subjectSemester;

//    @ManyToOne
//    @JoinColumn(name = "teacher_id", referencedColumnName = "id")
//    private Teacher teacher;
}
