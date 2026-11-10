package com.kelvin.spring_restapi.entities.dtos;

import jakarta.validation.constraints.Email;
import jakarta.validation.constraints.NotBlank;
import jakarta.validation.constraints.NotNull;
import jakarta.validation.constraints.Pattern;

public record AddParentDto(
        @NotBlank(message = "Name is required") String name,
       @NotBlank(message = "Email is required") @Email(regexp = "^[A-Za-z0-9._%+-]+@[A-Za-z0-9.-]+\\.[A-Za-z]{2,}$",
               message = "Please enter valid email address") String email,
        @NotBlank(message = "Phone number is required")
        @Pattern(regexp = "^(\\+254|0)[17]\\d{8}$",
                message = "Invalid international phone number format")
        String phoneNumber,
        @NotBlank(message = "Residence is required") String residence
) {
}
