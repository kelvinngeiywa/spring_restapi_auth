package com.kelvin.spring_restapi.repository;

import com.kelvin.spring_restapi.entities.User;
import org.springframework.data.jpa.repository.JpaRepository;

import java.util.Optional;
import java.util.UUID;

public interface UserRepository extends JpaRepository<User, UUID> {
    Optional<User> findUserByEmail(String email);
    boolean existsByEmail(String email);
    boolean existsByTeacherId(UUID teacherId);
}
