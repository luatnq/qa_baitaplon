package com.example.qa.data.entity;

import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;
import org.hibernate.annotations.Proxy;

import javax.persistence.*;
import java.io.Serializable;
import java.util.Set;

@Entity
@Table(name = "teachers")
@Data
@AllArgsConstructor
@NoArgsConstructor
@Proxy(lazy=false)
@PrimaryKeyJoinColumn(name = "teacher_id")
public class Teacher extends User implements Serializable {
    @Column(name = "major")
    private String major;

    @ManyToMany
    @JoinTable(name = "teacher_subject",
            joinColumns = @JoinColumn(name = "teacher_id", referencedColumnName = "id"),
            inverseJoinColumns = @JoinColumn(name = "subject_id", referencedColumnName = "id"))
    private Set<Subject> subjects;
}
