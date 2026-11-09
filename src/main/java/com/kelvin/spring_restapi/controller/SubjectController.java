package com.kelvin.spring_restapi.controller;

import com.kelvin.spring_restapi.entities.Subject;
import com.kelvin.spring_restapi.entities.dtos.AddSubjectDto;
import com.kelvin.spring_restapi.entities.dtos.ResponseDto;
import com.kelvin.spring_restapi.services.SubjectService;
import jakarta.validation.Valid;
import org.hibernate.mapping.Map;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.util.List;
import java.util.UUID;

@RestController
@RequestMapping("/subjects")
public class SubjectController {

    private final SubjectService subjectService;

    public SubjectController(SubjectService subjectService){
        this.subjectService=subjectService;

    }

    @PostMapping
    public ResponseEntity<ResponseDto<Subject>> addSubject(@Valid @RequestBody AddSubjectDto addSubjectDto){
        Subject newSubject= subjectService.AddSubject(addSubjectDto);
        ResponseDto<Subject> responseDto=new ResponseDto<>(HttpStatus.CREATED.value(),"Subject created successfully!!",newSubject);
        return ResponseEntity.status(HttpStatus.CREATED).body(responseDto);

    }

    @GetMapping
    public ResponseEntity<ResponseDto<List<Subject>>> getAllSubjects(){
        List<Subject> subjects= subjectService.getAllSubjects();
        ResponseDto<List<Subject>> responseDto=new ResponseDto<>(HttpStatus.OK.value(),"Subjects found",subjects);
        return ResponseEntity.status(HttpStatus.OK).body(responseDto);
    }

    @GetMapping("/{id}")
    public ResponseEntity<ResponseDto<Subject>> getSubjectById(@PathVariable UUID id){
        Subject subject= subjectService.getSubjectById(id);
        ResponseDto<Subject> responseDto=new ResponseDto<>(HttpStatus.OK.value(),"Subject found",subject);
        return ResponseEntity.status(HttpStatus.OK.value()).body(responseDto);
    }
}
