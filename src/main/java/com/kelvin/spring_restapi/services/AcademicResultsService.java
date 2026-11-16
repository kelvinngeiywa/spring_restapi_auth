package com.kelvin.spring_restapi.services;

import com.kelvin.spring_restapi.entities.Student;
import com.kelvin.spring_restapi.entities.dtos.AcademicResultsResponseDto;
import com.kelvin.spring_restapi.entities.dtos.AddResultDto;
import com.kelvin.spring_restapi.repository.AcademicResultRepository;
import com.kelvin.spring_restapi.repository.StudentRepository;
import com.kelvin.spring_restapi.repository.SubjectRepository;

public class AcademicResultsService {
    private final AcademicResultRepository academicResultRepository;
    private final StudentRepository studentRepository;
    private final SubjectRepository subjectRepository;

    public AcademicResultsService(AcademicResultRepository academicResultRepository,SubjectRepository subjectRepository,
                                  StudentRepository studentRepository
    ){
        this.academicResultRepository=academicResultRepository;
        this.studentRepository=studentRepository;
        this.subjectRepository=subjectRepository;

    }

    public AcademicResultsResponseDto addResults(AddResultDto addResultDto){
        Student student= studentRepository.findStudentById(addResultDto.student().getId());

    }


}
