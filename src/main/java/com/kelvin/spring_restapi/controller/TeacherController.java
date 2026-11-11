package com.kelvin.spring_restapi.controller;

import com.kelvin.spring_restapi.entities.Teacher;
import com.kelvin.spring_restapi.entities.dtos.*;
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
    public ResponseEntity<ApiResponseDto<TeacherResponseDto>> addNewTeacher(@Valid @RequestBody AddTeacherDto addTeacherDto){
        TeacherResponseDto newTeacher= teacherService.createTeacher(addTeacherDto);
        ApiResponseDto<TeacherResponseDto> responseDto=new ApiResponseDto<>(HttpStatus.CREATED.value(),"Teacher created successfully!!",newTeacher);
        return ResponseEntity.status(HttpStatus.CREATED).body(responseDto);
    }

    @PostMapping("/assign-subject/{teacherId}")
    public ResponseEntity<ApiResponseDto<TeacherResponseDto>> assignSubject(@PathVariable UUID teacherId,
                                                              @Valid @RequestBody
                                                              AssignSubjectDto assignSubjectDto){
        TeacherResponseDto newSubjectAssigned= teacherService.assignSubjects(teacherId,assignSubjectDto.subjectIds());
        ApiResponseDto<TeacherResponseDto> responseDto=new ApiResponseDto<>(HttpStatus.CREATED.value(),
                "Subject assigned",newSubjectAssigned);
        return ResponseEntity.status(HttpStatus.CREATED).body(responseDto);
    }

    @DeleteMapping("/{teacherId}/subjects/{subjectId}")
    public ResponseEntity<ApiResponseDto<TeacherResponseDto>> removeSubject(@PathVariable UUID teacherId,@PathVariable UUID subjectId){
        TeacherResponseDto teacher= teacherService.removeSubject(teacherId,subjectId);
        ApiResponseDto<TeacherResponseDto> responseDto= new ApiResponseDto<>(HttpStatus.OK.value(), "",teacher);
        return ResponseEntity.status(HttpStatus.OK).body(responseDto);
    }

    @GetMapping
    public ResponseEntity<ApiResponseDto<List<TeacherResponseDto>>> getAllTeachers(){
        List<TeacherResponseDto> teachers=teacherService.getAllTeachers();
        ApiResponseDto<List<TeacherResponseDto>> responseDto= new ApiResponseDto<>(HttpStatus.OK.value(),"Teachers found",teachers);
        return ResponseEntity.status(HttpStatus.OK).body(responseDto);
    }

    @GetMapping("/{id}")
    public ResponseEntity<ApiResponseDto<TeacherResponseDto>> getTeacherById(@PathVariable UUID id){
        TeacherResponseDto teacher= teacherService.getTeacherById(id);
        ApiResponseDto<TeacherResponseDto> responseDto=new ApiResponseDto<>(HttpStatus.OK.value(), "Teacher found",teacher);
        return ResponseEntity.status(HttpStatus.OK).body(responseDto);

    }

    @PutMapping("/{id}")
    public ResponseEntity<ApiResponseDto<TeacherResponseDto>> updateTeacher(@PathVariable UUID id,
                                                                            @Valid @RequestBody  AddTeacherDto teacherDto){
        TeacherResponseDto teacher=teacherService.updateTeacher(id,teacherDto);
        ApiResponseDto<TeacherResponseDto> responseDto= new ApiResponseDto<>(HttpStatus.OK.value(),
                "Teacher updated",teacher);

        return ResponseEntity.status(HttpStatus.OK).body(responseDto);
    }

    @DeleteMapping("/{id}")
    public ResponseEntity<ApiResponseDto<String>> deleteTeacher(@PathVariable UUID id){
        String results=teacherService.deleteTeacher(id);
        ApiResponseDto<String> responseDto= new ApiResponseDto<>(HttpStatus.OK.value(), "Teacher deleted",
                results);
        return ResponseEntity.status(HttpStatus.OK).body(responseDto);
    }
}
