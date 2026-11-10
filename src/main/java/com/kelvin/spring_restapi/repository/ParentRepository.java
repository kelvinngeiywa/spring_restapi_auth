package com.kelvin.spring_restapi.repository;

import com.kelvin.spring_restapi.entities.Parent;
import org.springframework.data.jpa.repository.JpaRepository;

import java.util.UUID;

public interface ParentRepository extends JpaRepository<Parent, UUID> {
    boolean existsByEmail(String email);
    boolean existsByPhoneNumber(String phoneNumber);
}
