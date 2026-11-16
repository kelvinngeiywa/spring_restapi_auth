package com.kelvin.spring_restapi.entities.dtos;

import com.kelvin.spring_restapi.entities.Role;

import java.util.UUID;

public record UserResponseDto(UUID id, String email, String role) {
}
