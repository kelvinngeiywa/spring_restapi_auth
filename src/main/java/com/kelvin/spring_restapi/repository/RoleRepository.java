package com.kelvin.spring_restapi.repository;

import com.kelvin.spring_restapi.entities.Role;
import com.kelvin.spring_restapi.entities.enums.RoleEnum;
import org.springframework.data.jpa.repository.JpaRepository;

import java.util.Optional;
import java.util.UUID;

public interface RoleRepository extends JpaRepository<Role, UUID> {
    Optional<Role> findRoleByName(RoleEnum name);
}
