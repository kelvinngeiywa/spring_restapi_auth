package com.kelvin.spring_restapi.repository;

import com.kelvin.spring_restapi.entities.Subject;
import org.springframework.data.jpa.repository.JpaRepository;

import java.util.Optional;
import java.util.UUID;

public interface SubjectRepository extends JpaRepository<Subject, UUID> {
    boolean existsBySubjectName(String subjectName);
    boolean existsBySubjectCode(String subjectCode);
    Optional<Subject> findSubjectBySubjectCode(String subjectCode);
}
