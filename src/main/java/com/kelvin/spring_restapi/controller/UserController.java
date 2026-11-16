package com.kelvin.spring_restapi.controller;

import com.kelvin.spring_restapi.entities.User;
import com.kelvin.spring_restapi.entities.dtos.ApiResponseDto;
import com.kelvin.spring_restapi.entities.dtos.CreateCredentialsDto;
import com.kelvin.spring_restapi.entities.dtos.ResponseDto;
import com.kelvin.spring_restapi.entities.dtos.UserResponseDto;
import com.kelvin.spring_restapi.services.UserService;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@RestController
@RequestMapping("/users")
public class UserController {
    private final UserService userService;

    public UserController(UserService userService){
        this.userService=userService;
    }

    @GetMapping
    public ResponseEntity<ApiResponseDto<List<UserResponseDto>>> getAllUsers(){
        List<UserResponseDto> results= userService.getAllUsers();
        ApiResponseDto<List<UserResponseDto>> responseDto= new ApiResponseDto<>(HttpStatus.OK.value(), "",results);
        return ResponseEntity.status(HttpStatus.OK).body(responseDto);
    }

    @PostMapping("/create-user")
    public ResponseEntity<ApiResponseDto<String>> createUser(@RequestBody CreateCredentialsDto credentialsDto){
        String results= userService.createCredentials(credentialsDto);
        ApiResponseDto<String> responseDto= new ApiResponseDto<>(HttpStatus.OK.value(),results.toString(),results);
        return ResponseEntity.status(HttpStatus.OK).body(responseDto);

    }


}
