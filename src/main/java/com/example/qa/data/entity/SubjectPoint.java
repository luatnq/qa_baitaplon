package com.example.qa.data.entity;


import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

import javax.persistence.*;
import java.io.Serializable;

@Entity
@Table(name = "subject_point")
@Data
@AllArgsConstructor
@NoArgsConstructor
public class SubjectPoint implements Serializable {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private int id;

    @Column(name = "factor")
    private int factor;

    @ManyToOne
    @JoinColumn(name = "point_id", referencedColumnName = "id")
    private Point point;

    @ManyToOne
    @JoinColumn(name = "subject_id", referencedColumnName = "id")
    private Subject subject;
}
