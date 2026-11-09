package com.kelvin.spring_restapi.services;

import com.kelvin.spring_restapi.customExceptions.DuplicateEntryException;
import com.kelvin.spring_restapi.entities.Subject;
import com.kelvin.spring_restapi.entities.Teacher;
import com.kelvin.spring_restapi.entities.dtos.AddTeacherDto;
import com.kelvin.spring_restapi.repository.TeacherRepository;
import jakarta.persistence.EntityNotFoundException;
import org.springframework.http.HttpStatus;
import org.springframework.stereotype.Service;
import org.springframework.web.server.ResponseStatusException;

import java.util.List;
import java.util.Set;
import java.util.UUID;

@Service
public class TeacherService {
    private final TeacherRepository teacherRepository;

    public TeacherService(TeacherRepository teacherRepository){
        this.teacherRepository=teacherRepository;
    }

    public Teacher createTeacher(AddTeacherDto teacherDto){
        if(teacherRepository.existsByEmail(teacherDto.getEmail())){
            throw new DuplicateEntryException("Email exists");

        }
        if(teacherRepository.existsByPhoneNumber(teacherDto.getPhoneNumber())){
            throw new DuplicateEntryException("Phone number exists");

        }

        Teacher newTeacher= new Teacher();
        newTeacher.setName(teacherDto.getName());
        newTeacher.setEmail(teacherDto.getEmail());
        newTeacher.setPhoneNumber(teacherDto.getPhoneNumber());
        newTeacher.setResidence(teacherDto.getResidence());
        newTeacher.setDob(teacherDto.getDob());

        teacherRepository.save(newTeacher);

        return newTeacher;

    }

    public List<Teacher> getAllTeachers(){
        List<Teacher> teachers= teacherRepository.findAll();

        if(teachers.isEmpty()){
            throw new ResponseStatusException(HttpStatus.NOT_FOUND,"No teacher found");
        }
        return teachers;

    }

    public Teacher assignSubjects(UUID teacherId, Set<UUID> subjectIds){
        Teacher teacher=teacherRepository.findById(teacherId).
                orElseThrow(()->new EntityNotFoundException("Teacher not found!!"));

    }


}
