package com.example.minipro_studentmanage.repository;

import com.example.minipro_studentmanage.entity.Course;
import com.example.minipro_studentmanage.entity.Student;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.query.Param;
import org.springframework.stereotype.Repository;

import java.util.List;
import java.util.Optional;

@Repository
public interface CourseRepository extends JpaRepository<Course, Long> {
    Optional<Course> findCourseByCode(String Code);


    @Query(
            value = """
            SELECT * FROM Course c
            WHERE c.id LIKE :keyword
            OR LOWER(c.code) LIKE :keyword
            OR LOWER(c.name) LIKE :keyword
            ORDER BY id ASC
""", nativeQuery = true
    )
    List<Course> getListCourse(String keyword);

}
