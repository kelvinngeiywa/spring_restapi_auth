package com.kelvin.spring_restapi.bootstrap;

import com.kelvin.spring_restapi.entities.Role;
import com.kelvin.spring_restapi.entities.User;
import com.kelvin.spring_restapi.entities.dtos.RegisterUserDto;
import com.kelvin.spring_restapi.entities.enums.RoleEnum;
import com.kelvin.spring_restapi.repository.RoleRepository;
import com.kelvin.spring_restapi.repository.UserRepository;
import jakarta.persistence.EntityNotFoundException;
import org.springframework.context.ApplicationListener;
import org.springframework.context.event.ContextRefreshedEvent;
import org.springframework.security.crypto.password.PasswordEncoder;
import org.springframework.stereotype.Component;

import java.util.HashSet;
import java.util.Optional;
import java.util.Set;

@Component
public class SuperAdminSeeder implements ApplicationListener<ContextRefreshedEvent> {
    private final RoleRepository roleRepository;
    private final UserRepository userRepository;
    private final PasswordEncoder passwordEncoder;

    public SuperAdminSeeder(RoleRepository roleRepository,UserRepository userRepository,
                            PasswordEncoder passwordEncoder){
        this.roleRepository=roleRepository;
        this.userRepository=userRepository;
        this.passwordEncoder=passwordEncoder;

    }
    @Override
    public void onApplicationEvent(ContextRefreshedEvent contextRefreshedEvent){
        this.createSuperAdmin();
    }

    private void createSuperAdmin(){
        RegisterUserDto registerUser= new RegisterUserDto("super.admin@gmail.com","Superadmin@123");

        Role role= roleRepository.findRoleByName(RoleEnum.SUPER_ADMIN).orElseThrow(
                ()-> new EntityNotFoundException("Super admin role not found!!")
        );

        Optional<User> user= userRepository.findUserByEmail(registerUser.email());

        if(user.isPresent()){
            return;
        }



        var userDto=new User();
        userDto.setEmail(registerUser.email());
        userDto.setPassword(passwordEncoder.encode(registerUser.password()));
        userDto.setRole(new HashSet<>(Set.of(role)));

        userRepository.save(userDto);

    }
}
