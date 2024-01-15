package com.example.minipro_studentmanage.dto.course.request;

import jakarta.persistence.Column;
import jakarta.validation.constraints.NotBlank;
import lombok.Getter;
import lombok.Setter;
import lombok.ToString;

@Getter
@Setter
@ToString
public class CreateCourseRequest {
    @NotBlank
    private String code;

    @NotBlank
    private String name;
}
