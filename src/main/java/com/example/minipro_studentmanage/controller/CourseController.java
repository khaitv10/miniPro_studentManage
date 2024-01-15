package com.example.minipro_studentmanage.controller;


import com.example.minipro_studentmanage.common.ApiResponse;
import com.example.minipro_studentmanage.dto.course.request.CreateCourseRequest;
import com.example.minipro_studentmanage.dto.course.response.CourseResponse;
import com.example.minipro_studentmanage.dto.course.response.UpdateCourseRequest;
import com.example.minipro_studentmanage.dto.student.request.CreateStudentRequest;
import com.example.minipro_studentmanage.dto.student.response.StudentResponse;
import com.example.minipro_studentmanage.services.CourseService;
import com.example.minipro_studentmanage.services.StudentService;
import lombok.RequiredArgsConstructor;
import lombok.extern.slf4j.Slf4j;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.validation.annotation.Validated;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@RestController
@RequestMapping(value = "/course")
@Slf4j
@RequiredArgsConstructor
public class CourseController {

    private final CourseService courseService;

    @PostMapping(value = "/add")
    public ResponseEntity<ApiResponse<CourseResponse>> addCourse(@Validated @RequestBody CreateCourseRequest newCourse) {
        log.info("Has request add course with data: {}", newCourse.toString());
        ApiResponse<CourseResponse> response = courseService.add(newCourse);
        log.info("Has response add course with data: {}", response.toString());
        return new ResponseEntity<>(response, HttpStatus.OK);
    }

    @GetMapping(value = "/all")
    public ResponseEntity<ApiResponse<List<CourseResponse>>> getAllCourses() {
       // log.info("Has request get list course with data: {}", courses.toString());
        List<CourseResponse> courseList = courseService.getAllCourses();
        ApiResponse<List<CourseResponse>> response = new ApiResponse<List<CourseResponse>>().ok(courseList);
        //log.info("Has response get list course with data: {}", response.toString());
        return new ResponseEntity<>(response, HttpStatus.OK);
    }

    @PutMapping(value = "/update")
    public ResponseEntity<ApiResponse<CourseResponse>> update(@RequestBody UpdateCourseRequest request) {
        log.info("Has request update course with code: {}", request.getCode());
        ApiResponse<CourseResponse> response = courseService.updateCourse(request);
        log.info("Has response update course with data: {}", response.toString());
        return new ResponseEntity<>(response, HttpStatus.OK);
    }

    @DeleteMapping(value = "/delete/{code}")
    public ResponseEntity<ApiResponse<CourseResponse>> delete(@PathVariable(name= "code") String code){
        log.info("Has request delete course with code: {}", code);
        ApiResponse<CourseResponse> response = courseService.deleteCourse(code);
        log.info("Has response delete course with data: {}", response.toString());
        return new ResponseEntity<>(response, HttpStatus.OK);
    }

}
