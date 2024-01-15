package com.example.minipro_studentmanage.dto.course.response;

import jakarta.validation.constraints.NotNull;
import lombok.Getter;
import lombok.Setter;

@Getter
@Setter
public class UpdateCourseRequest {
    private Long id;
    private String name;
    @NotNull(message = "Enter code for update")
    private String code;
}
