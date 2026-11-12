package com.kelvin.spring_restapi.controller;

import com.kelvin.spring_restapi.entities.dtos.ApiResponseDto;
import com.kelvin.spring_restapi.entities.dtos.LoginRequestDto;
import com.kelvin.spring_restapi.entities.dtos.LoginResponseDto;
import com.kelvin.spring_restapi.entities.dtos.ResponseDto;
import com.kelvin.spring_restapi.services.AuthService;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

@RestController
@RequestMapping("/auth")
public class AuthController {
    private final AuthService authService;

    public AuthController(AuthService authService){
        this.authService=authService;
    }
    @PostMapping("/login")
    public ResponseEntity<ApiResponseDto<LoginResponseDto>> login(@RequestBody LoginRequestDto loginRequest){
        LoginResponseDto results=authService.loginUser(loginRequest);
        ApiResponseDto<LoginResponseDto> responseDto=new ApiResponseDto<>(HttpStatus.OK.value(), "Login successful",
                results);
        return ResponseEntity.status(HttpStatus.OK).body(responseDto);
    }
}
