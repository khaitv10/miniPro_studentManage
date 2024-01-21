package com.example.minipro_studentmanage.dto.student.request;


import jakarta.validation.constraints.NotBlank;
import lombok.Getter;
import lombok.Setter;
import lombok.ToString;

@Getter
@Setter
@ToString
public class CreateStudentRequest {

    @NotBlank(message = "Name is mandatory")
    private String fullname;
    @NotBlank(message = "Code is mandatory")
    private String code;
    @NotBlank(message = "Address is mandatory")
    private String address;
}
