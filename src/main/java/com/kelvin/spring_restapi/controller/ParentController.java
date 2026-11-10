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
import java.util.UUID;

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

    @GetMapping("/{id}")
    public ResponseEntity<ResponseDto<ParentResponseDto>> getParentById(@PathVariable UUID id){
        ParentResponseDto parent= parentService.getParentById(id);
        ResponseDto<ParentResponseDto> responseDto= new ResponseDto<>(HttpStatus.OK.value(), "Parent found",
                parent);
        return ResponseEntity.status(HttpStatus.OK).body(responseDto);
    }

    @PutMapping("/{id}")
    public ResponseEntity<ResponseDto<ParentResponseDto>> updateParent(@PathVariable UUID id,@RequestBody AddParentDto parentDto){
        ParentResponseDto updateParent= parentService.updateParent(id,parentDto);
        ResponseDto<ParentResponseDto> responseDto= new ResponseDto<>(HttpStatus.OK.value(), "Parent updated!!",
                updateParent);
        return ResponseEntity.status(HttpStatus.OK).body(responseDto);

    }

    @DeleteMapping("/{id}")
    public ResponseEntity<ResponseDto<String>> deleteParent(@PathVariable UUID id){
        String deleteParent= parentService.deleteParent(id);
        ResponseDto<String> responseDto=new ResponseDto<>(HttpStatus.OK.value(), "Parent deleted!!",
                deleteParent);
        return ResponseEntity.status(HttpStatus.OK).body(responseDto);

    }
}
