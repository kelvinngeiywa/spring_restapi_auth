package com.kelvin.spring_restapi.controller;

import com.kelvin.spring_restapi.entities.dtos.AcademicResultsResponseDto;
import com.kelvin.spring_restapi.entities.dtos.AddResultDto;
import com.kelvin.spring_restapi.entities.dtos.ApiResponseDto;
import com.kelvin.spring_restapi.services.AcademicResultsService;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.security.access.prepost.PreAuthorize;
import org.springframework.web.bind.annotation.*;

import java.util.List;
import java.util.UUID;

@RestController
@RequestMapping("academic-results")
public class AcademicResultsController {
    private final AcademicResultsService academicResultsService;

    public AcademicResultsController(AcademicResultsService academicResultsService){
        this.academicResultsService=academicResultsService;

    }

    @GetMapping
    @PreAuthorize("hasRole('TEACHER')")
    public ResponseEntity<ApiResponseDto<List<AcademicResultsResponseDto>>> getAllResults(){
        List<AcademicResultsResponseDto> results= academicResultsService.getAllResults();
        ApiResponseDto<List<AcademicResultsResponseDto>> responseDto= new ApiResponseDto<>(
                HttpStatus.OK.value(), "Results found",results
        );
        return ResponseEntity.status(HttpStatus.OK).body(responseDto);
    }

    @PostMapping
    @PreAuthorize("hasRole('TEACHER')")
    public ResponseEntity<ApiResponseDto<AcademicResultsResponseDto>> addResults(@RequestBody AddResultDto addResultDto){
        AcademicResultsResponseDto results= academicResultsService.addResults(addResultDto);
        ApiResponseDto<AcademicResultsResponseDto> responseDto= new ApiResponseDto<>(
                HttpStatus.CREATED.value(), "Results added",results
        );

        return ResponseEntity.status(HttpStatus.CREATED).body(responseDto);
    }

    @PutMapping("/{id}")
    @PreAuthorize("hasRole('TEACHER')")
    public ResponseEntity<ApiResponseDto<AcademicResultsResponseDto>> updateResults(@PathVariable UUID id,
                                                                                    @RequestBody AddResultDto addResultDto){
        AcademicResultsResponseDto results= academicResultsService.updateResults(id,addResultDto);
        ApiResponseDto<AcademicResultsResponseDto> responseDto= new ApiResponseDto<>(
                HttpStatus.OK.value(), "Results updated",results
        );

        return ResponseEntity.status(HttpStatus.OK).body(responseDto);

    }

}
