package com.kelvin.spring_restapi.controller;

import com.kelvin.spring_restapi.entities.dtos.ApiResponseDto;
import com.kelvin.spring_restapi.entities.dtos.AssignAdminDto;
import com.kelvin.spring_restapi.entities.dtos.CreateCredentialsDto;
import com.kelvin.spring_restapi.entities.dtos.UserResponseDto;
import com.kelvin.spring_restapi.services.UserService;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.security.access.prepost.PreAuthorize;
import org.springframework.web.bind.annotation.*;

@RestController
@RequestMapping("/super-admin")
@PreAuthorize("hasRole('SUPER_ADMIN')")
public class SuperAdminController {
    private final UserService userService;

    public SuperAdminController(UserService userService){
        this.userService=userService;
    }

    @PostMapping
    public ResponseEntity<ApiResponseDto<UserResponseDto>> createAdminRole(@RequestBody CreateCredentialsDto createAdmin){
        UserResponseDto results= userService.createAdmin(createAdmin);
        ApiResponseDto<UserResponseDto> responseDto= new ApiResponseDto<>(HttpStatus.CREATED.value(), "Admin created",
                results);
        return ResponseEntity.status(HttpStatus.CREATED).body(responseDto);
    }


    @PutMapping
    public ResponseEntity<ApiResponseDto<UserResponseDto>> assignAdminRole(@RequestBody AssignAdminDto assignRole){
        UserResponseDto results= userService.assignAdminRole(assignRole);
        ApiResponseDto<UserResponseDto> responseDto= new ApiResponseDto<>(HttpStatus.OK.value(), "Admin role assigned",
                results);
        return ResponseEntity.status(HttpStatus.OK).body(responseDto);
    }
}
