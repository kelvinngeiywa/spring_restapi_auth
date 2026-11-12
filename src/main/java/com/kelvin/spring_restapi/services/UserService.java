package com.kelvin.spring_restapi.services;

import com.kelvin.spring_restapi.customExceptions.DuplicateEntryException;
import com.kelvin.spring_restapi.entities.Role;
import com.kelvin.spring_restapi.entities.Teacher;
import com.kelvin.spring_restapi.entities.User;
import com.kelvin.spring_restapi.entities.dtos.CreateCredentialsDto;
import com.kelvin.spring_restapi.entities.enums.RoleEnum;
import com.kelvin.spring_restapi.repository.*;
import jakarta.persistence.EntityNotFoundException;
import org.springframework.security.crypto.password.PasswordEncoder;
import org.springframework.stereotype.Service;

import java.util.Optional;

@Service
public class UserService {
    private final UserRepository userRepository;
    private final ParentRepository parentRepository;
    private final TeacherRepository teacherRepository;
    private final StudentRepository studentRepository;
    private final PasswordEncoder passwordEncoder;
    private final RoleRepository roleRepository;

    public UserService(UserRepository userRepository,ParentRepository parentRepository,
                       TeacherRepository teacherRepository,StudentRepository studentRepository,
                       PasswordEncoder passwordEncoder,RoleRepository roleRepository){
        this.userRepository=userRepository;
        this.parentRepository=parentRepository;
        this.teacherRepository=teacherRepository;
        this.studentRepository=studentRepository;
        this.passwordEncoder=passwordEncoder;
        this.roleRepository=roleRepository;
    }

    public String createCredentials(CreateCredentialsDto credentialsDto){
        switch (credentialsDto.accountType()){
            case Teacher -> {
                Teacher teacher=teacherRepository.findTeacherByEmail(credentialsDto.email()
                );
                boolean exists=userRepository.existsByTeacherId(teacher.getId());
                System.out.println(exists);
                if(!exists){
                    Optional<Role> optionalRole= roleRepository.findRoleByName(RoleEnum.TEACHER);
                    User newUser= new User();
                    newUser.setEmail(credentialsDto.email());
                    newUser.setPassword(passwordEncoder.encode("Teacher1@123"));
                    newUser.setTeacher(teacher);
                    newUser.setRole(optionalRole.get());

                    userRepository.save(newUser);
                    return "Teacher's account creation";

                }


            }

            case Parent -> {
                return "This is for parents";
            }
        }

        return "";

    }
}
