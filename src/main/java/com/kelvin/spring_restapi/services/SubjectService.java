package com.kelvin.spring_restapi.services;

import com.kelvin.spring_restapi.customExceptions.DuplicateEntryException;
import com.kelvin.spring_restapi.entities.Subject;
import com.kelvin.spring_restapi.entities.dtos.AddSubjectDto;
import com.kelvin.spring_restapi.entities.dtos.SubjectResponseDto;
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

    public SubjectResponseDto AddSubject(AddSubjectDto addSubjectDto){
        if(addSubjectDto.subjectName().isEmpty()){
            throw new DuplicateEntryException("Subject name is required");
        }

        if(addSubjectDto.subjectCode().isEmpty()){
            throw new DuplicateEntryException("Subject code is required");
        }
        if(subjectRepository.existsBySubjectName(addSubjectDto.subjectName())){
            throw new DuplicateEntryException("Subject with name "+addSubjectDto.subjectName()+
                    " exists");
        }

        if(subjectRepository.existsBySubjectCode(addSubjectDto.subjectCode())){
            throw new DuplicateEntryException("Subject with code "+addSubjectDto.subjectCode()+
                    " exists");
        }
        Subject newSubject=new Subject();
        newSubject.setSubjectName(addSubjectDto.subjectName());
        newSubject.setSubjectCode(addSubjectDto.subjectCode());

        subjectRepository.save(newSubject);
        return toSubjectResponse(newSubject);

    }

    public List<SubjectResponseDto> getAllSubjects(){
        return subjectRepository.findAll().stream().map(this::toSubjectResponse).toList();

    }

    public SubjectResponseDto getSubjectById(UUID id){
        Subject subject= subjectRepository.findById(id).orElseThrow(()-> new ResponseStatusException(
                HttpStatus.NOT_FOUND,"Subject not found!!!"
        ));

        return toSubjectResponse(subject);
    }

    private SubjectResponseDto toSubjectResponse(Subject subject){
        return new SubjectResponseDto(
                subject.getId(),subject.getSubjectName(),subject.getSubjectCode()
        );
    }
}
