package com.kelvin.spring_restapi.entities.dtos;

import jakarta.validation.constraints.Email;
import jakarta.validation.constraints.NotBlank;
import jakarta.validation.constraints.NotNull;
import jakarta.validation.constraints.Past;

import java.time.LocalDate;

public record AddTeacherDto(
        @NotNull(message = "Name is required")
        String name,
        @NotNull(message = "Phone number is required")
        String phoneNumber,
        @NotNull(message = "Email is required")
        @Email(regexp = "^[A-Za-z0-9._%+-]+@[A-Za-z0-9.-]+\\.[A-Za-z]{2,}$",
                message = "Please enter valid email address")
        String email,
        @NotNull(message = "DOB is required")
        @Past(message = "DOB must be in the past")
        LocalDate dob,
        @NotBlank(message = "Residence is required")
        String residence
) {

}
