package com.example.minipro_studentmanage.services;

import com.example.minipro_studentmanage.common.ApiResponse;
import com.example.minipro_studentmanage.dto.course.request.CreateCourseRequest;
import com.example.minipro_studentmanage.dto.course.request.GetListCourseRequest;
import com.example.minipro_studentmanage.dto.course.response.CourseResponse;
import com.example.minipro_studentmanage.dto.course.request.UpdateCourseRequest;
import com.example.minipro_studentmanage.entity.Course;
import com.example.minipro_studentmanage.entity.Student;
import com.example.minipro_studentmanage.enums.ResponseCode;
import com.example.minipro_studentmanage.exception.BusinessException;
import com.example.minipro_studentmanage.repository.CourseRepository;
import org.apache.commons.lang3.StringUtils;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.List;
import java.util.Objects;
import java.util.stream.Collectors;

@Service
public class CourseService {

    @Autowired
    private CourseRepository courseRepository;

    // add course
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


    public List<CourseResponse> getAllCourses(GetListCourseRequest request) {
        if(StringUtils.isBlank(request.getKeyword())) {
            request.setKeyword("");
        }
        String keyword = "%" + request.getKeyword() + "%";
        List<Course> courseList = courseRepository.getListCourse(keyword);
        return courseList.stream().map(CourseResponse::new).collect(Collectors.toList());
    }


    public ApiResponse<CourseResponse> updateCourse(UpdateCourseRequest request) {

        Course course = courseRepository.findCourseByCode(request.getCode()).orElse(null);
        if (Objects.isNull(course)) {
            throw new BusinessException(ResponseCode.COURSE_NOT_FOUND);
        }

        if (StringUtils.isNotBlank(request.getCode())) {
            Course courseFind = courseRepository.findCourseByCode(request.getCode()).orElse(null);
            if (Objects.nonNull(courseFind) && !courseFind.getId().equals(course.getId())) {
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
