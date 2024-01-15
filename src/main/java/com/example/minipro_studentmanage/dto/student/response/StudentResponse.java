package com.example.minipro_studentmanage.dto.student.response;

import com.example.minipro_studentmanage.entity.Student;
import lombok.Getter;
import lombok.Setter;
import lombok.ToString;

@Getter
@Setter
@ToString
public class StudentResponse {
    private Long id;
    private String name;
    private String code;
    private String address;


    public StudentResponse(Student student) {
        this.id = student.getId();
        this.name = student.getFullName();
        this.code = student.getCode();
        this.address = student.getAddress();
    }

}
