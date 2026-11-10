package com.kelvin.spring_restapi.services;

import com.kelvin.spring_restapi.customExceptions.DuplicateEntryException;
import com.kelvin.spring_restapi.entities.Parent;
import com.kelvin.spring_restapi.entities.Student;
import com.kelvin.spring_restapi.entities.dtos.AddStudentDto;
import com.kelvin.spring_restapi.entities.dtos.StudentResponseDto;
import com.kelvin.spring_restapi.repository.ParentRepository;
import com.kelvin.spring_restapi.repository.StudentRepository;
import jakarta.persistence.EntityNotFoundException;
import org.springframework.stereotype.Service;

import java.util.List;
import java.util.UUID;

@Service
public class StudentService {
    private final StudentRepository studentRepository;
    private final ParentRepository parentRepository;

    public StudentService(StudentRepository studentRepository,ParentRepository parentRepository){
        this.studentRepository=studentRepository;
        this.parentRepository=parentRepository;

    }

    public StudentResponseDto addStudent(AddStudentDto addStudentDto){
        if(studentRepository.existsByRegNumber(addStudentDto.regNumber())){
            throw new DuplicateEntryException("Student with registration number "+addStudentDto.regNumber()+" exists");
        }

        Parent parent= parentRepository.findById(addStudentDto.parentId()).orElseThrow(()->
                new EntityNotFoundException("Parent not found"));


        Student newStudent= new Student();
        newStudent.setName(addStudentDto.name());
        newStudent.setGrade(addStudentDto.grade());
        newStudent.setRegNumber(addStudentDto.regNumber());
        newStudent.setStream(addStudentDto.stream());
        newStudent.setDob(addStudentDto.dob());
        newStudent.setResidence(addStudentDto.residence());
        newStudent.setParent(parent);

        studentRepository.save(newStudent);

        return toStudentResponse(newStudent);
    }

    public List<StudentResponseDto> getAllStudents(){
        return studentRepository.findAll().stream().map(this::toStudentResponse).toList();
    }

    public StudentResponseDto getStudentById(UUID id){
        Student student= studentRepository.findById(id).orElseThrow(
                ()-> new EntityNotFoundException("Student not found")
        );

        return toStudentResponse(student);
    }

    public StudentResponseDto updateStudent(UUID id, AddStudentDto studentDto){
        Student student= studentRepository.findById(id).orElseThrow(
                ()-> new EntityNotFoundException("Student not found")
        );

        if(!student.getRegNumber().equals(studentDto.regNumber()) &&
        studentRepository.existsByRegNumber(studentDto.regNumber())){
            throw new DuplicateEntryException("Student with registration number "+studentDto.regNumber()+" exists");

        }

        Parent parent= parentRepository.findById(studentDto.parentId()).orElseThrow(()->
                new EntityNotFoundException("Parent not found"));

        student.setName(studentDto.name());
        student.setDob(studentDto.dob());
        student.setStream(studentDto.stream());
        student.setGrade(studentDto.grade());
        student.setResidence(studentDto.residence());
        student.setRegNumber(studentDto.regNumber());
        student.setParent(parent);

        studentRepository.save(student);

        return toStudentResponse(student);

    }

    public String deleteStudent(UUID id){
        Student student= studentRepository.findById(id).orElseThrow(
                ()-> new EntityNotFoundException("Student not found")
        );

        studentRepository.delete(student);
        return "";

    }

    private StudentResponseDto toStudentResponse(Student student){
        return new StudentResponseDto(
                student.getId(),student.getName(),student.getRegNumber(),student.getGrade(),
                student.getStream(),student.getDob(),student.getResidence()
        );
    }


}
