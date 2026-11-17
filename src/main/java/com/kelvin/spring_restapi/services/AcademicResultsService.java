package com.kelvin.spring_restapi.services;

import com.kelvin.spring_restapi.entities.AcademicResults;
import com.kelvin.spring_restapi.entities.Student;
import com.kelvin.spring_restapi.entities.Subject;
import com.kelvin.spring_restapi.entities.Teacher;
import com.kelvin.spring_restapi.entities.dtos.AcademicResultsResponseDto;
import com.kelvin.spring_restapi.entities.dtos.AddResultDto;
import com.kelvin.spring_restapi.repository.AcademicResultRepository;
import com.kelvin.spring_restapi.repository.StudentRepository;
import com.kelvin.spring_restapi.repository.SubjectRepository;
import com.kelvin.spring_restapi.repository.TeacherRepository;
import jakarta.persistence.EntityNotFoundException;
import org.springframework.stereotype.Service;

import java.util.List;
import java.util.Optional;
import java.util.UUID;

@Service
public class AcademicResultsService {
    private final AcademicResultRepository academicResultRepository;
    private final StudentRepository studentRepository;
    private final SubjectRepository subjectRepository;
    private final TeacherRepository teacherRepository;

    public AcademicResultsService(AcademicResultRepository academicResultRepository,SubjectRepository subjectRepository,
                                  StudentRepository studentRepository,TeacherRepository teacherRepository
    ){
        this.academicResultRepository=academicResultRepository;
        this.studentRepository=studentRepository;
        this.subjectRepository=subjectRepository;
        this.teacherRepository=teacherRepository;

    }

    public List<AcademicResultsResponseDto> getAllResults(){
        return academicResultRepository.findAll().stream().map(this::toAcademicResultsResponseDto).toList();
    }

    public AcademicResultsResponseDto addResults(AddResultDto addResultDto){
        Student student= studentRepository.findStudentByRegNumber(addResultDto.regNumber()).orElseThrow(()->
                new EntityNotFoundException("Student not found!!"));
        Teacher teacher= teacherRepository.findTeacherById(addResultDto.teacherId()).orElseThrow(
                ()-> new EntityNotFoundException("Teacher not found")
        );

        Subject subject=subjectRepository.findSubjectBySubjectCode(addResultDto.subjectCode()).orElseThrow(
                ()-> new EntityNotFoundException("Subject not found!!")
        );
        AcademicResults academicResults= new AcademicResults();

        academicResults.setStudent(student);
        academicResults.setSubject(subject);
        academicResults.setScore(addResultDto.score());
        academicResults.setMaxScore(addResultDto.maxScore());
        academicResults.setExamType(addResultDto.examType());
        academicResults.setTerm(addResultDto.term());
        academicResults.setAcademicYear(addResultDto.academicYear().getYear());
        academicResults.setTeacher(teacher);
        academicResults.setRemarks(addResultDto.remarks());

        academicResultRepository.save(academicResults);

        return toAcademicResultsResponseDto(academicResults);
    }

    public AcademicResultsResponseDto updateResults(UUID id,AddResultDto addResultDto){
        AcademicResults academicResults= academicResultRepository.findAcademicResultsById(id).orElseThrow(
                ()-> new EntityNotFoundException("Academic results not found!!")
        );
        academicResults.setScore(addResultDto.score());
        academicResults.setMaxScore(addResultDto.maxScore());
        academicResultRepository.save(academicResults);

        return toAcademicResultsResponseDto(academicResults);

    }

    private AcademicResultsResponseDto toAcademicResultsResponseDto(AcademicResults academicResults){
        return new AcademicResultsResponseDto(
                academicResults.getStudent().getRegNumber(),academicResults.getSubject().getSubjectCode(),academicResults.getScore(),academicResults.getMaxScore(),
                academicResults.getTerm(),academicResults.getExamType(),academicResults.getAcademicYear(),academicResults.getTeacher().getId(),
                academicResults.getRemarks()
        );
    }


}
