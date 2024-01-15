package com.example.minipro_studentmanage.dto.student.request;


import jakarta.validation.constraints.NotBlank;
import lombok.Getter;
import lombok.Setter;
import lombok.ToString;

@Getter
@Setter
@ToString
public class CreateStudentRequest {

    @NotBlank
    private String fullname;
    @NotBlank
    private String code;
    @NotBlank
    private String address;
}
