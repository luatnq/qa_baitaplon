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
}
