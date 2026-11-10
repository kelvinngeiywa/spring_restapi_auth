package com.kelvin.spring_restapi.entities.dtos;

import com.kelvin.spring_restapi.entities.enums.GradeStreamEnum;
import jakarta.validation.constraints.NotBlank;
import jakarta.validation.constraints.NotNull;

import java.time.LocalDate;
import java.util.UUID;

public record AddStudentDto(
        @NotBlank(message = "Name is required") String name,
        @NotBlank(message = "Registration number is required") String regNumber,
        @NotNull(message = "Grade is required") int grade,
        @NotBlank(message = "Stream is required")GradeStreamEnum stream,
        @NotBlank(message = "DOB is required")LocalDate dob,
        @NotBlank(message = "Residence is required") String residence,
        @NotNull(message = "Parent is required")UUID parentId


        ) {
}
