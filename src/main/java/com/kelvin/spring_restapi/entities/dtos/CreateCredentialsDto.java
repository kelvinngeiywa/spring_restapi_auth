package com.kelvin.spring_restapi.entities.dtos;

import com.kelvin.spring_restapi.entities.enums.AccountType;
import jakarta.validation.constraints.NotBlank;
import jakarta.validation.constraints.NotNull;
import jakarta.validation.constraints.Pattern;

public record CreateCredentialsDto(
        @NotNull AccountType accountType,
        @NotBlank String email,
        @Pattern(
                regexp = "^(?=.*[0-9])(?=.*[a-z])(?=.*[A-Z])(?=.*[@#$%^&-+=()])(?=\\S+$).{8,72}$",
                message = "Password must be at least 8 characters long, contain at least one digit, one uppercase, one lowercase, and one special character."
        )
        @NotBlank String password,
        String regNumber
        ) {
}
