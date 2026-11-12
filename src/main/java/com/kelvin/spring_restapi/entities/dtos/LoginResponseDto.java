package com.kelvin.spring_restapi.entities.dtos;

public record LoginResponseDto(
        String token,
        long expiresIn
) {
}
