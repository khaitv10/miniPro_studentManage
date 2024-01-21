package com.example.minipro_studentmanage.repository;

import com.example.minipro_studentmanage.entity.Course;
import com.example.minipro_studentmanage.entity.Grade;
import com.example.minipro_studentmanage.entity.Student;
import jakarta.transaction.Transactional;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Modifying;
import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.query.Param;
import org.springframework.stereotype.Repository;

import java.util.List;
import java.util.Optional;

@Repository
public interface GradeRepository extends JpaRepository<Grade, Long> {
    @Query(value = """
    SELECT * FROM Grade g 
    WHERE g.id like :keyword
""", nativeQuery = true)
    List<Grade> listGrade(String keyword);

    @Query(value = """
    SELECT * FROM Grade g 
    WHERE g.student_code = :stdCode
    AND g.course_code = :courCode
""", nativeQuery = true)
    Grade findGradeByStudentCodeAndCourseCode(@Param("stdCode") String stdCode, @Param("courCode") String courCode);


    List<Grade> findAllByCourse (Course course);


}
