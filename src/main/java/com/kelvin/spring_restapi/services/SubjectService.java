package com.kelvin.spring_restapi.services;

import com.kelvin.spring_restapi.customExceptions.DuplicateEntryException;
import com.kelvin.spring_restapi.entities.Subject;
import com.kelvin.spring_restapi.entities.dtos.AddSubjectDto;
import com.kelvin.spring_restapi.repository.SubjectRepository;
import org.springframework.http.HttpStatus;
import org.springframework.stereotype.Service;
import org.springframework.web.server.ResponseStatusException;

import java.util.List;
import java.util.UUID;

@Service
public class SubjectService {
    private final SubjectRepository subjectRepository;

    public SubjectService(SubjectRepository subjectRepository){
        this.subjectRepository=subjectRepository;

    }

    public Subject AddSubject(AddSubjectDto addSubjectDto){
        if(addSubjectDto.getSubjectName().isEmpty()){
            throw new DuplicateEntryException("Subject name is required");
        }

        if(addSubjectDto.getSubjectCode().isEmpty()){
            throw new DuplicateEntryException("Subject code is required");
        }
        if(subjectRepository.existsBySubjectName(addSubjectDto.getSubjectName())){
            throw new DuplicateEntryException("Subject with name "+addSubjectDto.getSubjectName()+
                    " exists");
        }

        if(subjectRepository.existsBySubjectCode(addSubjectDto.getSubjectCode())){
            throw new DuplicateEntryException("Subject with code "+addSubjectDto.getSubjectCode()+
                    " exists");
        }
        Subject newSubject=new Subject();
        newSubject.setSubjectName(addSubjectDto.getSubjectName());
        newSubject.setSubjectCode(addSubjectDto.getSubjectCode());

        subjectRepository.save(newSubject);
        return newSubject;

    }

    public List<Subject> getAllSubjects(){
        List<Subject> subjects= subjectRepository.findAll();

        if(subjects.isEmpty()){
            throw new ResponseStatusException(HttpStatus.NOT_FOUND,"No subject found");
        }
        return subjects;
    }

    public Subject getSubjectById(UUID id){
        return subjectRepository.findById(id).orElseThrow(()-> new ResponseStatusException(
                HttpStatus.NOT_FOUND,"Subject not found!!!"
        ));
    }
}
