package com.kelvin.spring_restapi.configs;

import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;
import org.springframework.security.authentication.AuthenticationProvider;
import org.springframework.security.config.Customizer;
import org.springframework.security.config.annotation.web.builders.HttpSecurity;
import org.springframework.security.config.annotation.web.configuration.EnableWebSecurity;
import org.springframework.security.config.http.SessionCreationPolicy;
import org.springframework.security.web.SecurityFilterChain;
import org.springframework.security.web.authentication.UsernamePasswordAuthenticationFilter;

@Configuration
@EnableWebSecurity
public class SecurityConfigs {
    private final AuthenticationProvider authProvider;
    private final JwtAuthFilter jwtAuthFilter;
    private final ApiAuthenticationEntryPoint authEntryPoint;
    private final RestAccessDeniedHandler restAccessDeniedHandler;

    public SecurityConfigs(AuthenticationProvider authProvider,JwtAuthFilter jwtAuthFilter,
                           ApiAuthenticationEntryPoint authEntryPoint,
                           RestAccessDeniedHandler accessDeniedHandler){
        this.authProvider=authProvider;
        this.jwtAuthFilter=jwtAuthFilter;
        this.authEntryPoint=authEntryPoint;
        this.restAccessDeniedHandler=accessDeniedHandler;

    }

    @Bean
    public SecurityFilterChain securityFilterChain(HttpSecurity http) throws Exception{
        return http
                .csrf(c->c.disable())
                .authorizeHttpRequests(auth->auth
                        .requestMatchers("/auth/login","/error").permitAll()
                        .anyRequest().authenticated())
                .sessionManagement(s->s.sessionCreationPolicy(SessionCreationPolicy.STATELESS))
                .exceptionHandling(ex->ex
                        .authenticationEntryPoint(authEntryPoint)
                        .accessDeniedHandler(restAccessDeniedHandler)
                )
                .authenticationProvider(authProvider)
                .addFilterBefore(jwtAuthFilter, UsernamePasswordAuthenticationFilter.class)
                .httpBasic(Customizer.withDefaults())
                .build();

    }
}
