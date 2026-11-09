package com.kelvin.spring_restapi.controller;

import com.kelvin.spring_restapi.entities.Teacher;
import com.kelvin.spring_restapi.entities.dtos.AddTeacherDto;
import com.kelvin.spring_restapi.entities.dtos.ResponseDto;
import com.kelvin.spring_restapi.services.TeacherService;
import jakarta.validation.Valid;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@RestController
@RequestMapping("/teachers")
public class TeacherController {
    private final TeacherService teacherService;

    public TeacherController(TeacherService teacherService){
        this.teacherService=teacherService;
    }

    @PostMapping
    public ResponseEntity<ResponseDto<Teacher>> addNewTeacher(@Valid @RequestBody AddTeacherDto addTeacherDto){
        Teacher newTeacher= teacherService.createTeacher(addTeacherDto);
        ResponseDto<Teacher> responseDto=new ResponseDto<>(HttpStatus.CREATED.value(),"Teacher created successfully!!",newTeacher);
        return ResponseEntity.status(HttpStatus.CREATED).body(responseDto);
    }

    @GetMapping
    public ResponseEntity<ResponseDto<List<Teacher>>> getAllSubjects(){
        List<Teacher> teachers=teacherService.getAllTeachers();
        ResponseDto<List<Teacher>> responseDto= new ResponseDto<>(HttpStatus.OK.value(),"Teachers found",teachers);
        return ResponseEntity.status(HttpStatus.OK).body(responseDto);
    }
}
