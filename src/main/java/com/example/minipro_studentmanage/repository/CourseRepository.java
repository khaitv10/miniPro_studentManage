package com.example.minipro_studentmanage.repository;

import com.example.minipro_studentmanage.entity.Course;
import com.example.minipro_studentmanage.entity.Student;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

import java.util.Optional;

@Repository
public interface CourseRepository extends JpaRepository<Course, Long> {
    Optional<Course> findCourseByCode(String code);
}
