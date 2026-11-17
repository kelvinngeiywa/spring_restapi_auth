package com.kelvin.spring_restapi.repository;

import com.kelvin.spring_restapi.entities.AcademicResults;
import org.springframework.data.jpa.repository.JpaRepository;

import java.util.Optional;
import java.util.UUID;

public interface AcademicResultRepository extends JpaRepository<AcademicResults, UUID> {
    boolean existsByStudentId(UUID studentId);
    Optional<AcademicResults> findAcademicResultsById(UUID id);
}
