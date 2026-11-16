package com.kelvin.spring_restapi.services;

import com.kelvin.spring_restapi.customExceptions.DuplicateEntryException;
import com.kelvin.spring_restapi.entities.Parent;
import com.kelvin.spring_restapi.entities.Role;
import com.kelvin.spring_restapi.entities.Teacher;
import com.kelvin.spring_restapi.entities.User;
import com.kelvin.spring_restapi.entities.dtos.CreateCredentialsDto;
import com.kelvin.spring_restapi.entities.dtos.UserResponseDto;
import com.kelvin.spring_restapi.entities.enums.RoleEnum;
import com.kelvin.spring_restapi.repository.*;
import jakarta.persistence.EntityNotFoundException;
import org.springframework.security.crypto.password.PasswordEncoder;
import org.springframework.stereotype.Service;

import java.util.List;
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

    public List<UserResponseDto> getAllUsers(){
        return userRepository.findAll().stream().map(this::toUserResponseDto).toList();

    }

    public String createCredentials(CreateCredentialsDto credentialsDto){
        User newUser= new User();
        return switch (credentialsDto.accountType()){
            case Teacher -> {
                Teacher teacher=teacherRepository.findTeacherByEmail(credentialsDto.email()
                ).orElseThrow(()-> new EntityNotFoundException("Teacher not found!!"));
                if(userRepository.existsByTeacherId(teacher.getId())){
                    throw new DuplicateEntryException("User account for teacher exists!!");
                }

                Optional<Role> optionalRole= roleRepository.findRoleByName(RoleEnum.TEACHER);

                newUser.setEmail(credentialsDto.email());
                newUser.setPassword(passwordEncoder.encode("Teacher1@123"));
                newUser.setTeacher(teacher);
                newUser.setRole(optionalRole.get());

                userRepository.save(newUser);
                yield "Teacher's account creation";




            }

            case Parent -> {
                Parent parent= parentRepository.findParentByEmail(credentialsDto.email()).orElseThrow(
                        ()-> new EntityNotFoundException("Parent not found!!")
                );
                if(userRepository.existsByParentId(parent.getId())){
                    throw new DuplicateEntryException("User account for parent exists");
                }


                Optional<Role> parentRole= roleRepository.findRoleByName(RoleEnum.PARENT);
                newUser.setEmail(credentialsDto.email());
                newUser.setPassword(passwordEncoder.encode("Parent1@123"));
                newUser.setParent(parent);
                newUser.setRole(parentRole.get());

                userRepository.save(newUser);
                yield "Parent created!!";
            }
        };
    }

    private UserResponseDto toUserResponseDto(User user){
        return new UserResponseDto(
                user.getId(),user.getEmail(),user.getRole().getName().toString()
        );
    }
}
