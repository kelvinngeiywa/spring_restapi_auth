package com.kelvin.spring_restapi.entities.dtos;

import com.kelvin.spring_restapi.entities.Student;
import com.kelvin.spring_restapi.entities.Subject;
import com.kelvin.spring_restapi.entities.Teacher;
import com.kelvin.spring_restapi.entities.enums.ExamTypeEnum;
import com.kelvin.spring_restapi.entities.enums.TermEnum;

import java.time.LocalDate;
import java.time.Year;
import java.util.UUID;

public record AcademicResultsResponseDto(
        String regNumber,
        String subjectCode,
        double max,
        double maxScore,
        TermEnum term,
        ExamTypeEnum examType,
        int academicYear,
        UUID teacherId,
        String remarks
) {
}
