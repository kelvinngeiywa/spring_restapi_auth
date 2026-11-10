package com.kelvin.spring_restapi.repository;

import com.kelvin.spring_restapi.entities.Student;
import org.springframework.data.jpa.repository.JpaRepository;

import java.util.UUID;

public interface StudentRepository extends JpaRepository<Student, UUID> {
    boolean existsByRegNumber(String regNumber);
}
