package com.kelvin.spring_restapi.entities.dtos;

import com.kelvin.spring_restapi.entities.enums.GradeStreamEnum;

import java.time.LocalDate;
import java.util.UUID;

public record StudentResponseDto(
        UUID id, String name, String regNumber, int grade, GradeStreamEnum stream, LocalDate dob,
        String residence
        ) {
}
