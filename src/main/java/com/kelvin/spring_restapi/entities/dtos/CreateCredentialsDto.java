package com.kelvin.spring_restapi.entities.dtos;

import com.kelvin.spring_restapi.entities.enums.AccountType;
import jakarta.validation.constraints.NotBlank;
import jakarta.validation.constraints.NotNull;

public record CreateCredentialsDto(
        @NotNull AccountType accountType,
        @NotBlank String email,
        String regNumber
        ) {
}
