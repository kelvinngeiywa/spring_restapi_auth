package com.kelvin.spring_restapi.controller;

import com.kelvin.spring_restapi.entities.dtos.AddParentDto;
import com.kelvin.spring_restapi.entities.dtos.ApiResponseDto;
import com.kelvin.spring_restapi.entities.dtos.ParentResponseDto;
import com.kelvin.spring_restapi.entities.dtos.ResponseDto;
import com.kelvin.spring_restapi.services.ParentService;
import jakarta.validation.Valid;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.util.List;
import java.util.UUID;

@RestController
@RequestMapping("/parents")
public class ParentController {
    private final ParentService parentService;

    public ParentController(ParentService parentService){
        this.parentService=parentService;
    }

    @PostMapping
    public ResponseEntity<ApiResponseDto<ParentResponseDto>> addParent(@Valid @RequestBody AddParentDto parentDto){
        ParentResponseDto newParent= parentService.addParent(parentDto);
        ApiResponseDto<ParentResponseDto> responseDto= new ApiResponseDto<>(HttpStatus.OK.value(), "",
                newParent);

        return ResponseEntity.status(HttpStatus.CREATED).body(responseDto);

    }

    @GetMapping
    public ResponseEntity<ApiResponseDto<List<ParentResponseDto>>> getAllParents(){
        List<ParentResponseDto> parents= parentService.getAllParents();
        ApiResponseDto<List<ParentResponseDto>> responseDto=new ApiResponseDto<>(
                HttpStatus.OK.value(), "",parents);

        return ResponseEntity.status(HttpStatus.OK).body(responseDto);

    }

    @GetMapping("/{id}")
    public ResponseEntity<ApiResponseDto<ParentResponseDto>> getParentById(@PathVariable UUID id){
        ParentResponseDto parent= parentService.getParentById(id);
        ApiResponseDto<ParentResponseDto> responseDto= new ApiResponseDto<>(HttpStatus.OK.value(), "Parent found",
                parent);
        return ResponseEntity.status(HttpStatus.OK).body(responseDto);
    }

    @PutMapping("/{id}")
    public ResponseEntity<ApiResponseDto<ParentResponseDto>> updateParent(@PathVariable UUID id,@RequestBody AddParentDto parentDto){
        ParentResponseDto updateParent= parentService.updateParent(id,parentDto);
        ApiResponseDto<ParentResponseDto> responseDto= new ApiResponseDto<>(HttpStatus.OK.value(), "Parent updated!!",
                updateParent);
        return ResponseEntity.status(HttpStatus.OK).body(responseDto);

    }

    @DeleteMapping("/{id}")
    public ResponseEntity<ApiResponseDto<String>> deleteParent(@PathVariable UUID id){
        String deleteParent= parentService.deleteParent(id);
        ApiResponseDto<String> responseDto=new ApiResponseDto<>(HttpStatus.OK.value(), "Parent deleted!!",
                deleteParent);
        return ResponseEntity.status(HttpStatus.OK).body(responseDto);

    }
}
