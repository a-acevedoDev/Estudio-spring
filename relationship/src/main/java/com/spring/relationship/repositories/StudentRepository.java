package com.spring.relationship.repositories;

import com.spring.relationship.entities.Student;
import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.CrudRepository;
import org.springframework.data.repository.query.Param;

import java.util.Optional;

public interface StudentRepository extends CrudRepository<Student, Long> {
    @Query("SELECT s FROM Student s LEFT JOIN FETCH s.courses WHERE s.id =?1")
    Optional<Student> findOneWithCourses(Long id);
}