package com.kelvin.spring_restapi.entities.dtos;

import jakarta.validation.constraints.NotEmpty;

import java.util.Set;
import java.util.UUID;

public record AssignSubjectDto(@NotEmpty Set<UUID> subjectIds) {
}
