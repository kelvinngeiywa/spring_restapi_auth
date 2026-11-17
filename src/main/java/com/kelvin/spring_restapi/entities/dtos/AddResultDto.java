package com.kelvin.spring_restapi.entities.dtos;

import com.kelvin.spring_restapi.entities.Student;
import com.kelvin.spring_restapi.entities.Subject;
import com.kelvin.spring_restapi.entities.Teacher;
import com.kelvin.spring_restapi.entities.enums.ExamTypeEnum;
import com.kelvin.spring_restapi.entities.enums.TermEnum;

import java.time.LocalDate;
import java.time.Year;
import java.util.UUID;

public record AddResultDto(
        String regNumber,
        String subjectCode,
        double score,
        double maxScore,
        TermEnum term,
        ExamTypeEnum examType,
        LocalDate academicYear,
        UUID teacherId,
        String remarks
) {
}
