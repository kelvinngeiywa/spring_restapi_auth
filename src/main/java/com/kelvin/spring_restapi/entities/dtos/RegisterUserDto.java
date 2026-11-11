package com.kelvin.spring_restapi.entities.dtos;

import jakarta.persistence.Column;
import jakarta.validation.constraints.Email;
import jakarta.validation.constraints.NotNull;
import jakarta.validation.constraints.Pattern;

public record RegisterUserDto(
        @NotNull(message = "Email is required")
        @Email(regexp = "^[A-Za-z0-9._%+-]+@[A-Za-z0-9.-]+\\.[A-Za-z]{2,}$",
                message = "Please enter valid email address")
        String email,
        @Column(nullable = false)
        @NotNull(message = "Password cannot be null")
        @Pattern(
                regexp = "^(?=.*[0-9])(?=.*[a-z])(?=.*[A-Z])(?=.*[@#$%^&-+=()])(?=\\S+$).{8,72}$",
                message = "Password must be at least 8 characters long, contain at least one digit, one uppercase, one lowercase, and one special character."
        )
        String password) {
}
