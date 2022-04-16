package com.example.qa.data.entity;

import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;
import org.springframework.beans.factory.annotation.Autowired;

import javax.persistence.*;
import java.io.Serializable;


@Data
@AllArgsConstructor
@NoArgsConstructor
@Entity
@Table(name ="semesters")
public class Semester implements Serializable {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private int id;

    @Column(name = "from_year")
    private int fromYear;

    @Column(name = "to_year")
    private int toYear;

    @Column(name = "semester_num")
    private int semesterNum;

}
