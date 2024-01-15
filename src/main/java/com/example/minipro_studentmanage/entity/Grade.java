package com.example.minipro_studentmanage.entity;

import jakarta.persistence.*;
import lombok.Getter;
import lombok.Setter;

@Entity
@Table(name = "grade")
@Getter
@Setter
public class Grade {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;

    @ManyToOne
    @JoinColumn(name = "student_code", referencedColumnName = "code")
    private Student student;

    @ManyToOne
    @JoinColumn(name = "course_code", referencedColumnName = "code")
    private Course course;

}

