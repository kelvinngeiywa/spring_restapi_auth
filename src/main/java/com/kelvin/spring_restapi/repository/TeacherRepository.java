package com.kelvin.spring_restapi.repository;

import com.kelvin.spring_restapi.entities.Teacher;
import org.springframework.data.jpa.repository.JpaRepository;

import java.util.Optional;
import java.util.UUID;

public interface TeacherRepository extends JpaRepository<Teacher, UUID> {
    boolean existsByEmail(String email);
    boolean existsByPhoneNumber(String phoneNumber);
    Teacher findTeacherByEmail(String email);
}
