package com.example.minipro_studentmanage.dto.student.request;


import lombok.Getter;
import lombok.Setter;
import lombok.ToString;
import org.springframework.data.domain.PageRequest;
import org.springframework.data.domain.Pageable;

@Getter
@Setter
@ToString
public class GetPageStudentRequest {

    private String keyword="";

    private Integer pageNumber = 0;
    private Integer pageSize = 10;

    public Pageable getPageable(){
        return PageRequest.of(pageNumber,pageSize);
    }
}
