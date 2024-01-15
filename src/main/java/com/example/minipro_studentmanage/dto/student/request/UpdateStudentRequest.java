package com.example.minipro_studentmanage.dto.student.request;


import jakarta.validation.constraints.NotNull;
import lombok.Getter;
import lombok.Setter;

@Getter
@Setter
public class UpdateStudentRequest {
    @NotNull(message = "Id is needed")
    private Long id;
    private String name;
    private String code;
    private String address;
}
