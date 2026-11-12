package com.kelvin.spring_restapi.entities.dtos;

public record LoginRequestDto(
        String email,
        String password
) {
}
