package com.kelvin.spring_restapi.controller;

import com.kelvin.spring_restapi.entities.Subject;
import com.kelvin.spring_restapi.entities.dtos.AddSubjectDto;
import com.kelvin.spring_restapi.entities.dtos.ApiResponseDto;
import com.kelvin.spring_restapi.entities.dtos.ResponseDto;
import com.kelvin.spring_restapi.entities.dtos.SubjectResponseDto;
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
    public ResponseEntity<ApiResponseDto<SubjectResponseDto>> addSubject(@Valid @RequestBody AddSubjectDto addSubjectDto){
        SubjectResponseDto newSubject= subjectService.AddSubject(addSubjectDto);
        ApiResponseDto<SubjectResponseDto> responseDto=new ApiResponseDto<>(HttpStatus.CREATED.value(),"Subject created successfully!!",newSubject);
        return ResponseEntity.status(HttpStatus.CREATED).body(responseDto);

    }

    @GetMapping
    public ResponseEntity<ApiResponseDto<List<SubjectResponseDto>>> getAllSubjects(){
        List<SubjectResponseDto> subjects= subjectService.getAllSubjects();
        ApiResponseDto<List<SubjectResponseDto>> responseDto=new ApiResponseDto<>(HttpStatus.OK.value(),"Subjects found",subjects);
        return ResponseEntity.status(HttpStatus.OK).body(responseDto);
    }

    @GetMapping("/{id}")
    public ResponseEntity<ApiResponseDto<SubjectResponseDto>> getSubjectById(@PathVariable UUID id){
        SubjectResponseDto subject= subjectService.getSubjectById(id);
        ApiResponseDto<SubjectResponseDto> responseDto=new ApiResponseDto<>(HttpStatus.OK.value(),"Subject found",subject);
        return ResponseEntity.status(HttpStatus.OK.value()).body(responseDto);
    }

    @PutMapping("/{id}")
    public ResponseEntity<ApiResponseDto<SubjectResponseDto>> updateSubject(@PathVariable UUID id,
                                                                           @Valid @RequestBody AddSubjectDto subjectDto){
        SubjectResponseDto results= subjectService.updateSubject(id,subjectDto);
        ApiResponseDto<SubjectResponseDto> responseDto= new ApiResponseDto<>(HttpStatus.OK.value(),
                "Subject updated",results);
        return ResponseEntity.status(HttpStatus.OK).body(responseDto);
    }

    @DeleteMapping("/{id}")
    public ResponseEntity<ApiResponseDto<String>> deleteSubject(@PathVariable UUID id){
        String results= subjectService.deleteSubject(id);
        ApiResponseDto<String> responseDto=new ApiResponseDto<>(HttpStatus.OK.value(), "Subject deleted",
                results);
        return ResponseEntity.status(HttpStatus.OK).body(responseDto);
    }
}
