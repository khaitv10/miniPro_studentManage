package com.example.minipro_studentmanage.dto.grade.response;

import com.example.minipro_studentmanage.entity.Grade;
import lombok.Getter;
import lombok.Setter;
import lombok.ToString;

@Getter
@Setter
@ToString
public class GradeResponse {
    private Long id;
    private Long idStudent;
    private String fullname;
    private String codeStudent;
    private Long idCourse;
    private String nameCourse;
    private String codeCourse;
    private Double grade;

    public GradeResponse(Grade grade){
        this.id = grade.getId();
        this.idStudent = grade.getStudent().getId();
        this.fullname = grade.getStudent().getFullName();
        this.codeStudent = grade.getStudent().getCode();
        this.idCourse = grade.getCourse().getId();
        this.nameCourse = grade.getCourse().getName();
        this.codeCourse = grade.getCourse().getCode();
        this.grade = grade.getGarde();
    }
}
