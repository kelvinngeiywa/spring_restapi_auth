package com.kelvin.spring_restapi.configs;

import com.kelvin.spring_restapi.repository.UserRepository;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;
import org.springframework.security.authentication.AuthenticationManager;
import org.springframework.security.authentication.AuthenticationProvider;
import org.springframework.security.authentication.dao.DaoAuthenticationProvider;
import org.springframework.security.config.annotation.authentication.configuration.AuthenticationConfiguration;
import org.springframework.security.core.userdetails.UserDetailsService;
import org.springframework.security.core.userdetails.UsernameNotFoundException;
import org.springframework.security.crypto.bcrypt.BCryptPasswordEncoder;

@Configuration
public class ApplicationConfigs {
    private final UserRepository userRepository;
    public ApplicationConfigs(UserRepository userRepository){
        this.userRepository=userRepository;
    }

    @Bean
    UserDetailsService userDetailsService(){
        return username -> userRepository.findUserByEmail(username).orElseThrow(()->
                new UsernameNotFoundException("User not found!!"));
    }

    public AuthenticationManager authenticationManager(AuthenticationConfiguration configurations)
    throws Exception{
        return configurations.getAuthenticationManager();
    }

    @Bean
    AuthenticationProvider authProvider(){
        DaoAuthenticationProvider authenticationProvider=new DaoAuthenticationProvider(userDetailsService());
        authenticationProvider.setPasswordEncoder(passwordEncoder());
        return authenticationProvider;
    }

    @Bean
    BCryptPasswordEncoder passwordEncoder(){
        return new BCryptPasswordEncoder();
    }
}
