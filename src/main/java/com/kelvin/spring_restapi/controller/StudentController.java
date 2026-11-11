package com.kelvin.spring_restapi.controller;

import com.kelvin.spring_restapi.entities.dtos.AddStudentDto;
import com.kelvin.spring_restapi.entities.dtos.ApiResponseDto;
import com.kelvin.spring_restapi.entities.dtos.ResponseDto;
import com.kelvin.spring_restapi.entities.dtos.StudentResponseDto;
import com.kelvin.spring_restapi.services.StudentService;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.util.List;
import java.util.UUID;

@RestController
@RequestMapping("/students")
public class StudentController {
    private final StudentService studentService;

    public StudentController(StudentService studentService){
        this.studentService=studentService;
    }

    @GetMapping
    public ResponseEntity<ApiResponseDto<List<StudentResponseDto>>> getAllStudents(){
        List<StudentResponseDto> students= studentService.getAllStudents();
        ApiResponseDto<List<StudentResponseDto>> responseDto= new ApiResponseDto<>(HttpStatus.OK.value(), "Students found",
                students);
        return ResponseEntity.status(HttpStatus.OK).body(responseDto);

    }

    @PostMapping
    public ResponseEntity<ApiResponseDto<StudentResponseDto>> addStudent(@RequestBody AddStudentDto studentDto){
        StudentResponseDto newStudent=studentService.addStudent(studentDto);
        ApiResponseDto<StudentResponseDto> responseDto=new ApiResponseDto<>(HttpStatus.OK.value(), "Student created",
                newStudent);
        return ResponseEntity.status(HttpStatus.OK).body(responseDto);

    }

    @GetMapping("/{id}")
    public ResponseEntity<ApiResponseDto<StudentResponseDto>> getStudentById(@PathVariable UUID id){
        StudentResponseDto student=studentService.getStudentById(id);
        ApiResponseDto<StudentResponseDto> responseDto=new ApiResponseDto<>(HttpStatus.OK.value(), "Student found",
                student);
        return ResponseEntity.status(HttpStatus.OK).body(responseDto);
    }

    @PutMapping("/{id}")
    public ResponseEntity<ApiResponseDto<StudentResponseDto>> updateStudent(@PathVariable UUID id,@RequestBody AddStudentDto studentDto){
        StudentResponseDto student=studentService.updateStudent(id,studentDto);
        ApiResponseDto<StudentResponseDto> responseDto=new ApiResponseDto<>(HttpStatus.OK.value(), "Student Updated!!",
                student);
        return ResponseEntity.status(HttpStatus.OK).body(responseDto);

    }

    @DeleteMapping("/{id}")
    public ResponseEntity<ApiResponseDto<String>> deleteStudent(@PathVariable UUID id){
        String student=studentService.deleteStudent(id);
        ApiResponseDto<String> responseDto=new ApiResponseDto<>(HttpStatus.OK.value(), "Student deleted!!",student);
        return ResponseEntity.status(HttpStatus.OK).body(responseDto);
    }
}
