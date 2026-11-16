package com.kelvin.spring_restapi.entities.dtos;

import com.kelvin.spring_restapi.entities.Student;
import com.kelvin.spring_restapi.entities.Subject;
import com.kelvin.spring_restapi.entities.Teacher;
import com.kelvin.spring_restapi.entities.enums.ExamTypeEnum;
import com.kelvin.spring_restapi.entities.enums.TermEnum;

import java.time.Year;

public record AddResultDto(
        Student student,
        Subject subject,
        double max,
        double maxScore,
        TermEnum term,
        ExamTypeEnum examType,
        Year academicYear,
        Teacher teacher,
        String remarks
) {
}
