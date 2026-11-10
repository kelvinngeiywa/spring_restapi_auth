package com.kelvin.spring_restapi.controller;

import com.kelvin.spring_restapi.entities.Teacher;
import com.kelvin.spring_restapi.entities.dtos.AddTeacherDto;
import com.kelvin.spring_restapi.entities.dtos.AssignSubjectDto;
import com.kelvin.spring_restapi.entities.dtos.ResponseDto;
import com.kelvin.spring_restapi.entities.dtos.TeacherResponseDto;
import com.kelvin.spring_restapi.services.TeacherService;
import jakarta.validation.Valid;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.util.List;
import java.util.UUID;

@RestController
@RequestMapping("/teachers")
public class TeacherController {
    private final TeacherService teacherService;

    public TeacherController(TeacherService teacherService){
        this.teacherService=teacherService;
    }

    @PostMapping
    public ResponseEntity<ResponseDto<TeacherResponseDto>> addNewTeacher(@Valid @RequestBody AddTeacherDto addTeacherDto){
        TeacherResponseDto newTeacher= teacherService.createTeacher(addTeacherDto);
        ResponseDto<TeacherResponseDto> responseDto=new ResponseDto<>(HttpStatus.CREATED.value(),"Teacher created successfully!!",newTeacher);
        return ResponseEntity.status(HttpStatus.CREATED).body(responseDto);
    }

    @PostMapping("/assign-subject/{teacherId}")
    public ResponseEntity<ResponseDto<TeacherResponseDto>> assignSubject(@PathVariable UUID teacherId,
                                                              @Valid @RequestBody
                                                              AssignSubjectDto assignSubjectDto){
        TeacherResponseDto newSubjectAssigned= teacherService.assignSubjects(teacherId,assignSubjectDto.subjectIds());
        ResponseDto<TeacherResponseDto> responseDto=new ResponseDto<>(HttpStatus.CREATED.value(),
                "Subject assigned",newSubjectAssigned);
        return ResponseEntity.status(HttpStatus.CREATED).body(responseDto);
    }

    @DeleteMapping("/{teacherId}/subjects/{subjectId}")
    public ResponseEntity<ResponseDto<TeacherResponseDto>> removeSubject(@PathVariable UUID teacherId,@PathVariable UUID subjectId){
        TeacherResponseDto teacher= teacherService.removeSubject(teacherId,subjectId);
        ResponseDto<TeacherResponseDto> responseDto= new ResponseDto<>(HttpStatus.OK.value(), "",teacher);
        return ResponseEntity.status(HttpStatus.OK).body(responseDto);
    }

    @GetMapping
    public ResponseEntity<ResponseDto<List<TeacherResponseDto>>> getAllSubjects(){
        List<TeacherResponseDto> teachers=teacherService.getAllTeachers();
        ResponseDto<List<TeacherResponseDto>> responseDto= new ResponseDto<>(HttpStatus.OK.value(),"Teachers found",teachers);
        return ResponseEntity.status(HttpStatus.OK).body(responseDto);
    }

    @GetMapping("/{id}")
    public ResponseEntity<ResponseDto<TeacherResponseDto>> getTeacherById(@PathVariable UUID id){
        TeacherResponseDto teacher= teacherService.getTeacherById(id);
        ResponseDto<TeacherResponseDto> responseDto=new ResponseDto<>(HttpStatus.OK.value(), "Teacher found",teacher);
        return ResponseEntity.status(HttpStatus.OK).body(responseDto);
    }
}
