package com.example.minipro_studentmanage.services;


import com.example.minipro_studentmanage.common.ApiResponse;
import com.example.minipro_studentmanage.dto.student.request.CreateStudentRequest;
import com.example.minipro_studentmanage.dto.student.request.GetPageStudentRequest;
import com.example.minipro_studentmanage.dto.student.request.UpdateStudentRequest;
import com.example.minipro_studentmanage.dto.student.response.PageStudentResponse;
import com.example.minipro_studentmanage.dto.student.response.StudentResponse;
import com.example.minipro_studentmanage.entity.Student;
import com.example.minipro_studentmanage.enums.ResponseCode;
import com.example.minipro_studentmanage.exception.BusinessException;
import com.example.minipro_studentmanage.repository.StudentRepository;
import org.apache.commons.lang3.StringUtils;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.domain.Page;
import org.springframework.stereotype.Service;

import java.util.ArrayList;
import java.util.List;
import java.util.Objects;

@Service
public class StudentService {

    @Autowired
    private StudentRepository studentRepository;

    public ApiResponse<StudentResponse> add(CreateStudentRequest request) throws BusinessException {
        Student student = studentRepository.findStudentByCode(request.getCode()).orElse(null);
        if (Objects.nonNull(student)) {
            throw new BusinessException(ResponseCode.STUDENT_CODE_NOT_UNIQUE);
        }

        student = new Student();
        student.setAddress(request.getAddress());
        student.setFullName(request.getFullname());
        student.setCode(request.getCode());
        Student studentSave = studentRepository.save(student);
        return new ApiResponse<StudentResponse>().ok(new StudentResponse(studentSave));
    }

// lay danh sach
//    public ApiResponse<PageStudentResponse> getPage(GetPageStudentRequest request) {
//
//        String keyword = "%" + request.getKeyword() + "%";
//
//        Page<Student> studentPage = studentRepository.searchStudent(keyword, request.getPageable());
//        PageStudentResponse pageStudentResponse = new PageStudentResponse();
//        pageStudentResponse.setPageNumber(studentPage.getNumber());
//        pageStudentResponse.setPageSize(studentPage.getSize());
//        List<StudentResponse> studentResponseList = new ArrayList<>();
//        for (Student student : studentPage.get().toList()) {
//            studentResponseList.add(new StudentResponse(student));
//        }
//        pageStudentResponse.setResponseList(studentResponseList);
//        return new ApiResponse<>().ok(pageStudentResponse);
//
//    }


    public ApiResponse<StudentResponse> getDetail(String id) throws BusinessException, NumberFormatException {
        Long idLong = null;
        try {
            idLong = Long.parseLong(id);
        } catch (NumberFormatException e) {
            throw new BusinessException(ResponseCode.STUDENT_ID_INCORRECT_FORMAT);
        }
        Student student = studentRepository.findById(idLong).orElse(null);
        if (Objects.isNull(student)) {
            throw new BusinessException(ResponseCode.STUDENT_NOT_FOUND);
        }
        return new ApiResponse<>().ok(new StudentResponse(student));
    }


    public ApiResponse<StudentResponse> updateStudent(UpdateStudentRequest request) {

        Student student = studentRepository.findById(request.getId()).orElse(null);
        if (Objects.isNull(student)) {
            throw new BusinessException(ResponseCode.STUDENT_NOT_FOUND);
        }

        if (StringUtils.isNotBlank(request.getCode())) {
            Student studentFindByCode = studentRepository.findStudentByCode(request.getCode()).orElse(null);
            if (Objects.nonNull(studentFindByCode) && !studentFindByCode.getCode().equals(student.getCode())) {
                throw new BusinessException(ResponseCode.STUDENT_CODE_NOT_UNIQUE);
            }
            student.setCode(request.getCode());
        }

        if (StringUtils.isNotBlank(request.getName())) {
            student.setFullName(request.getName());
        }

        if (StringUtils.isNotBlank(request.getAddress())) {
            student.setAddress(request.getAddress());
        }

        student = studentRepository.save(student);
        return new ApiResponse<>().ok(new StudentResponse(student));
    }


//    public ApiResponse<StudentResponse> deleteStudent(String code) throws BusinessException, NumberFormatException {
//        String stdCode = null;
//        Student student = studentRepository.findStudentByCode(stdCode).orElse(null);
//        if (Objects.isNull(student)) {
//            throw new BusinessException(ResponseCode.STUDENT_NOT_FOUND);
//        }
//        student = studentRepository.delete(student);
//        return new ApiResponse<>().ok(new StudentResponse(student));
//    }

    public ApiResponse<StudentResponse> deleteStudent(String code) throws BusinessException, NumberFormatException {
        Student student = studentRepository.findStudentByCode(code).orElse(null);
        if(Objects.isNull(student)) {
            throw new BusinessException(ResponseCode.STUDENT_NOT_FOUND);
        }
        studentRepository.delete(student);
        return new ApiResponse<>().ok(ResponseCode.SUCCESS);
    }
}

