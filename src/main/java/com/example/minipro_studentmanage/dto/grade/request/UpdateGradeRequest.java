package com.example.minipro_studentmanage.dto.grade.request;

import jakarta.validation.constraints.NotNull;
import jakarta.validation.constraints.PositiveOrZero;
import lombok.Getter;
import lombok.Setter;
import lombok.ToString;

@Getter
@Setter
@ToString
public class UpdateGradeRequest {
    @NotNull
    private Long id;
    private Long idStudent;
    private Long idCourse;
    @PositiveOrZero
    private Double grade;
}
