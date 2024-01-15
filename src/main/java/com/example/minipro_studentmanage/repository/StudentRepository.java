package com.example.minipro_studentmanage.repository;

import com.example.minipro_studentmanage.entity.Student;
import com.example.minipro_studentmanage.entity.Student;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;
import org.springframework.stereotype.Repository;

import java.util.Optional;


@Repository
public interface StudentRepository extends JpaRepository<Student, Long> {

    Optional<Student> findStudentByCode(String code);


    @Query(
            value = """
                            select * from Student as s
                            where (s.address like :keyword
                            or s.code like :keyword
                            or s.fullName like :keyword )
                    """, nativeQuery = true)
    Page<Student> searchStudent(String keyword, Pageable pageable);
}
