package com.kelvin.spring_restapi.services;

import com.kelvin.spring_restapi.customExceptions.DuplicateEntryException;
import com.kelvin.spring_restapi.entities.Subject;
import com.kelvin.spring_restapi.entities.Teacher;
import com.kelvin.spring_restapi.entities.dtos.AddTeacherDto;
import com.kelvin.spring_restapi.entities.dtos.SubjectResponseDto;
import com.kelvin.spring_restapi.entities.dtos.TeacherResponseDto;
import com.kelvin.spring_restapi.repository.SubjectRepository;
import com.kelvin.spring_restapi.repository.TeacherRepository;
import jakarta.persistence.EntityNotFoundException;
import org.springframework.http.HttpStatus;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;
import org.springframework.web.server.ResponseStatusException;

import java.util.HashSet;
import java.util.List;
import java.util.Set;
import java.util.UUID;
import java.util.stream.Collectors;

@Service
public class TeacherService {
    private final TeacherRepository teacherRepository;
    private final SubjectRepository subjectRepository;

    public TeacherService(TeacherRepository teacherRepository, SubjectRepository subjectRepository){
        this.teacherRepository=teacherRepository;
        this.subjectRepository = subjectRepository;
    }

    public TeacherResponseDto createTeacher(AddTeacherDto teacherDto){
        if(teacherRepository.existsByEmail(teacherDto.email())){
            throw new DuplicateEntryException("Email exists");

        }
        if(teacherRepository.existsByPhoneNumber(teacherDto.phoneNumber())){
            throw new DuplicateEntryException("Phone number exists");

        }

        Teacher newTeacher= new Teacher();
        newTeacher.setName(teacherDto.name());
        newTeacher.setEmail(teacherDto.email());
        newTeacher.setPhoneNumber(teacherDto.phoneNumber());
        newTeacher.setResidence(teacherDto.residence());
        newTeacher.setDob(teacherDto.dob());

        teacherRepository.save(newTeacher);

        return toTeacherResponse(newTeacher);

    }

    public List<TeacherResponseDto> getAllTeachers(){
        List<TeacherResponseDto> teachers= teacherRepository.findAll().stream().map(this::toTeacherResponse).toList();
        return teachers;

    }

    public TeacherResponseDto getTeacherById(UUID id){
        Teacher teacher= teacherRepository.findById(id).orElseThrow(()->new EntityNotFoundException("Teacher not found!!"));
        return toTeacherResponse(teacher);
    }

    @Transactional
    public TeacherResponseDto assignSubjects(UUID teacherId, Set<UUID> subjectIds){
        Teacher teacher=teacherRepository.findById(teacherId).
                orElseThrow(()->new EntityNotFoundException("Teacher not found!!"));
        List<Subject> subjects=subjectRepository.findAllById(subjectIds);

        if(subjects.size() != subjectIds.size()){
            throw new EntityNotFoundException("One or more subjects not found!!");
        }

//        teacher.addSubject(subjects.getFirst());
        subjects.forEach(teacher::addSubject);

        teacherRepository.save(teacher);
        return toTeacherResponse(teacher);

    }

    @Transactional
    public TeacherResponseDto removeSubject(UUID teacherId,UUID subjectId){
        Teacher teacher= teacherRepository.findById(teacherId).orElseThrow(()-> new EntityNotFoundException("Teacher not found"));

        Subject subject=subjectRepository.findById(subjectId).orElseThrow(()-> new EntityNotFoundException("Subject not found!!"));

        teacher.removeSubjects(subject);
        teacherRepository.save(teacher);
        return toTeacherResponse(teacher);
    }



    private TeacherResponseDto toTeacherResponse(Teacher teacher){
        return new TeacherResponseDto(
                teacher.getId(),teacher.getName(),teacher.getEmail(),teacher.getResidence(),teacher.getDob(),teacher.getResidence(),
                teacher.getSubjects().stream().map(
                        subject-> new SubjectResponseDto(
                                subject.getId(),subject.getSubjectName(),subject.getSubjectCode()))
                        .collect(Collectors.toSet())
        );

    }


}
