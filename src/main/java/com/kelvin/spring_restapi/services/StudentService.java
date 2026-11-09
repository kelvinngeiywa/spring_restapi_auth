package com.kelvin.spring_restapi.services;

import com.kelvin.spring_restapi.repository.StudentRepository;
import org.springframework.stereotype.Service;

@Service
public class StudentService {
    private final StudentRepository studentRepository;

    public StudentService(StudentRepository studentRepository){
        this.studentRepository=studentRepository;

    }
}
