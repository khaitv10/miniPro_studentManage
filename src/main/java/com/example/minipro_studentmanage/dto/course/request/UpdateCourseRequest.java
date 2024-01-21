package com.example.minipro_studentmanage.dto.course.request;

import jakarta.validation.constraints.NotNull;
import lombok.Getter;
import lombok.Setter;

@Getter
@Setter
public class UpdateCourseRequest {
    @NotNull(message = "Id is mandatory")
    private Long id;

    private String name;
    private String code;
}
