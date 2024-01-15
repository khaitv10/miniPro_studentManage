package com.example.minipro_studentmanage.repository;

import com.example.minipro_studentmanage.entity.Grade;
import com.example.minipro_studentmanage.entity.Student;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

import java.util.Optional;

@Repository
public interface GradeRepository extends JpaRepository<Grade, Long> {
    Optional<Grade> findGradeByStudentCode(String code);
}
