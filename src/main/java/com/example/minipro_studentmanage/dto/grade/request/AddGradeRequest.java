package com.example.minipro_studentmanage.dto.grade.request;


import jakarta.validation.constraints.NotNull;
import jakarta.validation.constraints.PositiveOrZero;
import lombok.Getter;
import lombok.Setter;
import lombok.ToString;

@Getter
@Setter
@ToString
public class AddGradeRequest {
    @NotNull
    private Long idStudent;
    @NotNull
    private Long idCourse;
    @NotNull
    @PositiveOrZero
    private Double grade;
}
