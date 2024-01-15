package com.example.minipro_studentmanage.dto.student.response;

import lombok.Getter;
import lombok.Setter;

import java.util.List;


@Getter
@Setter
public class PageStudentResponse {
    private Integer pageNumber;
    private Integer pageSize;

    private List<StudentResponse> responseList;
}
