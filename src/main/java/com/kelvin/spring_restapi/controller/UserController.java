package com.kelvin.spring_restapi.controller;

import com.kelvin.spring_restapi.entities.dtos.ApiResponseDto;
import com.kelvin.spring_restapi.entities.dtos.CreateCredentialsDto;
import com.kelvin.spring_restapi.entities.dtos.ResponseDto;
import com.kelvin.spring_restapi.services.UserService;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

@RestController
@RequestMapping("/users")
public class UserController {
    private final UserService userService;

    public UserController(UserService userService){
        this.userService=userService;
    }

    @PostMapping("/create-user")
    public ResponseEntity<ApiResponseDto<String>> createUser(@RequestBody CreateCredentialsDto credentialsDto){
        String results= userService.createCredentials(credentialsDto);
        ApiResponseDto<String> responseDto= new ApiResponseDto<>(HttpStatus.OK.value(),
                "Teacher's account creation",results);
        return ResponseEntity.status(HttpStatus.OK).body(responseDto);

    }


}
