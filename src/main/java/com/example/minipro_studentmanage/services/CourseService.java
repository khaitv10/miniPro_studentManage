package com.example.minipro_studentmanage.services;

import com.example.minipro_studentmanage.common.ApiResponse;
import com.example.minipro_studentmanage.dto.course.request.CreateCourseRequest;
import com.example.minipro_studentmanage.dto.course.response.CourseResponse;
import com.example.minipro_studentmanage.dto.course.response.UpdateCourseRequest;
import com.example.minipro_studentmanage.dto.student.request.CreateStudentRequest;
import com.example.minipro_studentmanage.dto.student.request.UpdateStudentRequest;
import com.example.minipro_studentmanage.dto.student.response.StudentResponse;
import com.example.minipro_studentmanage.entity.Course;
import com.example.minipro_studentmanage.entity.Student;
import com.example.minipro_studentmanage.enums.ResponseCode;
import com.example.minipro_studentmanage.exception.BusinessException;
import com.example.minipro_studentmanage.repository.CourseRepository;
import org.apache.commons.lang3.StringUtils;
import org.springframework.stereotype.Service;

import java.util.List;
import java.util.Objects;
import java.util.stream.Collectors;

@Service
public class CourseService {
    private CourseRepository courseRepository;

    public ApiResponse<CourseResponse> add(CreateCourseRequest newCourse) throws BusinessException {
        Course course = courseRepository.findCourseByCode(newCourse.getCode()).orElse(null);
        if (Objects.nonNull(course)) {
            throw new BusinessException(ResponseCode.COURSE_CODE_NOT_UNIQUE);
        }

        course = new Course();
        course.setCode(newCourse.getCode());
        course.setName(newCourse.getName());
        Course courseSave = courseRepository.save(course);
        return new ApiResponse<CourseResponse>().ok(new CourseResponse(courseSave));
    }

    public List<CourseResponse> getAllCourses() {
        return courseRepository.findAll().stream()
                .map(CourseResponse::new)
                .collect(Collectors.toList());
    }

    public ApiResponse<CourseResponse> updateCourse(UpdateCourseRequest request) {

        Course course = courseRepository.findCourseByCode(request.getCode()).orElse(null);
        if (Objects.isNull(course)) {
            throw new BusinessException(ResponseCode.COURSE_NOT_FOUND);
        }

        if (StringUtils.isNotBlank(request.getCode())) {
            Course courseFindByCode = courseRepository.findCourseByCode(request.getCode()).orElse(null);
            if (Objects.nonNull(courseFindByCode) && !courseFindByCode.getCode().equals(course.getCode())) {
                throw new BusinessException(ResponseCode.STUDENT_CODE_NOT_UNIQUE);
            }
            course.setCode(request.getCode());
        }

        if (StringUtils.isNotBlank(request.getName())) {
            course.setName(request.getName());
        }

        course = courseRepository.save(course);
        return new ApiResponse<>().ok(new CourseResponse(course));
    }


    public ApiResponse<CourseResponse> deleteCourse(String code) throws BusinessException, NumberFormatException {
        Course course = courseRepository.findCourseByCode(code).orElse(null);
        if(Objects.isNull(course)) {
            throw new BusinessException(ResponseCode.COURSE_NOT_FOUND);
        }
        courseRepository.delete(course);
        return new ApiResponse<>().ok(ResponseCode.SUCCESS);
    }
}
