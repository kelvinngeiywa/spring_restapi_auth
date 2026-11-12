package com.kelvin.spring_restapi.services;

import com.kelvin.spring_restapi.entities.User;
import com.kelvin.spring_restapi.entities.dtos.CreateCredentialsDto;
import com.kelvin.spring_restapi.entities.dtos.LoginRequestDto;
import com.kelvin.spring_restapi.entities.dtos.LoginResponseDto;
import com.kelvin.spring_restapi.entities.enums.AccountType;
import com.kelvin.spring_restapi.repository.UserRepository;
import org.springframework.security.authentication.AuthenticationManager;
import org.springframework.security.authentication.UsernamePasswordAuthenticationToken;
import org.springframework.security.crypto.password.PasswordEncoder;
import org.springframework.stereotype.Service;

@Service
public class AuthService {
    private final UserRepository userRepository;
    private final PasswordEncoder passwordEncoder;
    private final AuthenticationManager authManager;
    private final JwtServices jwtServices;

    public AuthService(UserRepository userRepository,PasswordEncoder passwordEncoder,
                       AuthenticationManager authManager,JwtServices jwtServices){
        this.userRepository=userRepository;
        this.passwordEncoder=passwordEncoder;
        this.authManager=authManager;
        this.jwtServices=jwtServices;

    }



    public LoginResponseDto loginUser(LoginRequestDto loginRequestDto){
        authManager.authenticate(
                new UsernamePasswordAuthenticationToken(
                        loginRequestDto.email(),
                        loginRequestDto.password()
                )
        );
        User user= userRepository.findUserByEmail(loginRequestDto.email()).orElseThrow();
        String jwtToken= jwtServices.generateToken(user);

        return new LoginResponseDto(jwtToken, jwtServices.getJwtExpirationTime());

    }
}
