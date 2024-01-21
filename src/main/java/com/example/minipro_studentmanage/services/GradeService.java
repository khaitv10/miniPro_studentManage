package com.example.minipro_studentmanage.services;

import com.example.minipro_studentmanage.common.ApiResponse;
import com.example.minipro_studentmanage.dto.course.request.GetListCourseRequest;
import com.example.minipro_studentmanage.dto.course.response.CourseResponse;
import com.example.minipro_studentmanage.dto.grade.request.*;
import com.example.minipro_studentmanage.dto.grade.response.GradeResponse;
import com.example.minipro_studentmanage.dto.student.response.StudentResponse;
import com.example.minipro_studentmanage.entity.Course;
import com.example.minipro_studentmanage.entity.Grade;
import com.example.minipro_studentmanage.entity.Student;
import com.example.minipro_studentmanage.enums.ResponseCode;
import com.example.minipro_studentmanage.exception.BusinessException;
import com.example.minipro_studentmanage.repository.CourseRepository;
import com.example.minipro_studentmanage.repository.GradeRepository;
import com.example.minipro_studentmanage.repository.StudentRepository;
import org.apache.commons.lang3.StringUtils;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Repository;
import org.springframework.stereotype.Service;

import java.util.List;
import java.util.Objects;
import java.util.stream.Collectors;

@Service
public class GradeService {
    @Autowired
    private StudentRepository studentRepository;

    @Autowired
    private CourseRepository courseRepository;

    @Autowired
    private GradeRepository gradeRepository;

    public GradeResponse addGrade(AddGradeRequest request){
        Student student = studentRepository.findById(request.getIdStudent()).orElse(null);
        if(Objects.isNull(student)) {
            throw new BusinessException(ResponseCode.STUDENT_NOT_FOUND);
        }

        Course course = courseRepository.findById(request.getIdCourse()).orElse(null);
        if(Objects.isNull(course)){
            throw new BusinessException(ResponseCode.COURSE_NOT_FOUND);
        }

        Grade grade = new Grade();
        grade.setGarde(request.getGrade());
        grade.setCourse(course);
        grade.setStudent(student);
        grade = gradeRepository.save(grade);
        return new GradeResponse(grade);
    }

    public GradeResponse updateGrade(UpdateGradeRequest request){

        Grade grade = gradeRepository.findById(request.getId()).orElse(null);
        if(Objects.isNull(grade)){
            throw new BusinessException(ResponseCode.GRADE_NOT_FOUND);
        }

        if(Objects.nonNull(request.getIdStudent()) && !request.getIdStudent().equals(0)){
            Student student = studentRepository.findById(request.getIdStudent()).orElse(null);
            if(Objects.isNull(student)) {
                throw new BusinessException(ResponseCode.STUDENT_NOT_FOUND);
            }
            grade.setStudent(student);
        }

        if(Objects.nonNull(request.getIdCourse()) && !request.getIdCourse().equals(0)){
            Course course = courseRepository.findById(request.getIdCourse()).orElse(null);
            if(Objects.isNull(course)){
                throw new BusinessException(ResponseCode.COURSE_NOT_FOUND);
            }
            grade.setCourse(course);
        }
        if(Objects.nonNull(request.getGrade())){
            grade.setGarde(request.getGrade());
        }
        grade = gradeRepository.save(grade);
        return new GradeResponse(grade);
    }

    public List<GradeResponse> getList(GetListGradeRequest request){
        if(StringUtils.isBlank(request.getKeyword())){
            request.setKeyword("");
        }

        String keyword = "%" + request.getKeyword() + "%";
        List<Grade> gradeResponseList = gradeRepository.listGrade(keyword);
        return gradeResponseList.stream().map(GradeResponse::new).collect(Collectors.toList());
    }


    public List<GradeResponse> getList(GetListStudentGradeByCourseRequest request){
        Course course = courseRepository.findById(request.getId()).orElse(null);
        List<Grade> gradeResponseList = gradeRepository.findAllByCourse(course);
        return gradeResponseList.stream().map(GradeResponse::new).collect(Collectors.toList());
    }

    public ApiResponse<GradeResponse> deleteCourse(DeleteGradeRequest request) throws BusinessException, NumberFormatException {
        Grade grade = gradeRepository.findGradeByStudentCodeAndCourseCode(request.getStudentCode(), request.getCourseCode());
        if(Objects.isNull(grade)) {
            throw new BusinessException(ResponseCode.GRADE_NOT_FOUND);
        }
        gradeRepository.delete(grade);
        return new ApiResponse<>().ok(ResponseCode.SUCCESS);
    }
}
