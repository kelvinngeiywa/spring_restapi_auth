package com.kelvin.spring_restapi.entities.dtos;

public record ApiResponseDto<T>(
        Integer status,
        String message,
        T data
) {
}
