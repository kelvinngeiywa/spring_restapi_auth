package com.kelvin.spring_restapi.controller;

import com.kelvin.spring_restapi.entities.dtos.AddParentDto;
import com.kelvin.spring_restapi.entities.dtos.ParentResponseDto;
import com.kelvin.spring_restapi.entities.dtos.ResponseDto;
import com.kelvin.spring_restapi.services.ParentService;
import jakarta.validation.Valid;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@RestController
@RequestMapping("/parents")
public class ParentController {
    private final ParentService parentService;

    public ParentController(ParentService parentService){
        this.parentService=parentService;
    }

    @PostMapping
    public ResponseEntity<ResponseDto<ParentResponseDto>> addParent(@Valid @RequestBody AddParentDto parentDto){
        ParentResponseDto newParent= parentService.addParent(parentDto);
        ResponseDto<ParentResponseDto> responseDto= new ResponseDto<>(HttpStatus.OK.value(), "",
                newParent);

        return ResponseEntity.status(HttpStatus.CREATED).body(responseDto);

    }

    @GetMapping
    public ResponseEntity<ResponseDto<List<ParentResponseDto>>> getAllParents(){
        List<ParentResponseDto> parents= parentService.getAllParents();
        ResponseDto<List<ParentResponseDto>> responseDto=new ResponseDto<>(
                HttpStatus.OK.value(), "",parents);

        return ResponseEntity.status(HttpStatus.OK).body(responseDto);

    }
}
