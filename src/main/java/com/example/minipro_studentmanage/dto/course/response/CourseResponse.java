package com.example.minipro_studentmanage.dto.course.response;

import com.example.minipro_studentmanage.entity.Course;
import lombok.Getter;
import lombok.Setter;
import lombok.ToString;

@Getter
@Setter
@ToString
public class CourseResponse {
    private Long id;
    private String name;
    private String code;

    public CourseResponse(Course course) {
        this.id = course.getId();
        this.name = course.getName();
        this.code = course.getCode();
    }

}
