package com.kelvin.spring_restapi.entities.dtos;

import java.time.LocalDate;
import java.util.Set;
import java.util.UUID;

public record TeacherResponseDto(UUID id, String name, String email, String phoneNumber,
                                 LocalDate dob,String residence, Set<SubjectResponseDto> subjects) {
}
