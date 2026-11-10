package com.kelvin.spring_restapi.entities.dtos;

import jakarta.persistence.Column;
import jakarta.validation.constraints.NotBlank;
import jakarta.validation.constraints.NotNull;

public record AddSubjectDto(   @Column(nullable = false)
                               @NotBlank(message = "Subject name is required")
                               String subjectName,
                               @Column(nullable = false)
                               @NotNull(message = "Subject code is required")
                               String subjectCode) {

}
