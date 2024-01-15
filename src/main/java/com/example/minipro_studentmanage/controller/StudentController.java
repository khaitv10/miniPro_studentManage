package com.example.minipro_studentmanage.controller;


import com.example.minipro_studentmanage.common.ApiResponse;
import com.example.minipro_studentmanage.dto.student.request.CreateStudentRequest;
import com.example.minipro_studentmanage.dto.student.request.GetPageStudentRequest;
import com.example.minipro_studentmanage.dto.student.request.UpdateStudentRequest;
import com.example.minipro_studentmanage.dto.student.response.PageStudentResponse;
import com.example.minipro_studentmanage.dto.student.response.StudentResponse;
import com.example.minipro_studentmanage.services.StudentService;
import lombok.RequiredArgsConstructor;
import lombok.extern.slf4j.Slf4j;
import org.springdoc.core.annotations.ParameterObject;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.validation.annotation.Validated;
import org.springframework.web.bind.annotation.*;

@RestController
@RequestMapping(value = "/student")
@Slf4j
@RequiredArgsConstructor
public class StudentController {

    private final StudentService studentService;


    @PostMapping(value = "/add")
    public ResponseEntity<ApiResponse<StudentResponse>> addStudent(@Validated @RequestBody CreateStudentRequest request) {
        log.info("Has request add student with data: {}", request.toString());
        ApiResponse<StudentResponse> response = studentService.add(request);
        log.info("Has response add student with data: {}", response.toString());
        return new ResponseEntity<>(response, HttpStatus.OK);
    }


//    @GetMapping(value = "/all")
//    public ResponseEntity<ApiResponse<PageStudentResponse>> getPage(@ParameterObject GetPageStudentRequest request) {
//        log.info("Has request get page student with data: {}", request.toString());
//        ApiResponse<PageStudentResponse> response = studentService.getPage(request);
//        log.info("Has response get page student with data: {}", response.toString());
//        return new ResponseEntity<>(response, HttpStatus.OK);
//    }


    @GetMapping(value = "/{id}")
    public ResponseEntity<ApiResponse<StudentResponse>> getDetail(@PathVariable(name = "id") String id) {
        log.info("Has request get student with id: {}", id);
        ApiResponse<StudentResponse> response = studentService.getDetail(id);
        log.info("Has response get student with data: {}", response.toString());
        return new ResponseEntity<>(response, HttpStatus.OK);
    }

    @PutMapping(value = "/update")
    public ResponseEntity<ApiResponse<StudentResponse>> update(@RequestBody UpdateStudentRequest request) {
        log.info("Has request update student with id: {}", request.getId());
        ApiResponse<StudentResponse> response = studentService.updateStudent(request);
        log.info("Has response update student with data: {}", response.toString());
        return new ResponseEntity<>(response, HttpStatus.OK);
    }

    @DeleteMapping(value = "/delete/{code}")
    public ResponseEntity<ApiResponse<StudentResponse>> delete(@PathVariable(name = "code") String code) {
        log.info("Has request delete student with code: {}", code);
        ApiResponse<StudentResponse> response = studentService.deleteStudent(code);
        log.info("Has response delete student with data: {}", response.toString());
        return new ResponseEntity<>(response, HttpStatus.OK);
    }

}
