package com.kelvin.spring_restapi.entities.dtos;

import java.util.UUID;

public record SubjectResponseDto(UUID id,String subjectName,String subjectCode) {
}
