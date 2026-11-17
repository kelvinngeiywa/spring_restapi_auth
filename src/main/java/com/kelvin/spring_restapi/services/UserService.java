package com.kelvin.spring_restapi.services;

import com.kelvin.spring_restapi.customExceptions.DuplicateEntryException;
import com.kelvin.spring_restapi.entities.Parent;
import com.kelvin.spring_restapi.entities.Role;
import com.kelvin.spring_restapi.entities.Teacher;
import com.kelvin.spring_restapi.entities.User;
import com.kelvin.spring_restapi.entities.dtos.AssignAdminDto;
import com.kelvin.spring_restapi.entities.dtos.CreateCredentialsDto;
import com.kelvin.spring_restapi.entities.dtos.UserResponseDto;
import com.kelvin.spring_restapi.entities.enums.RoleEnum;
import com.kelvin.spring_restapi.repository.*;
import jakarta.persistence.EntityNotFoundException;
import org.springframework.security.crypto.password.PasswordEncoder;
import org.springframework.stereotype.Service;

import java.util.HashSet;
import java.util.List;
import java.util.Optional;
import java.util.Set;

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

                Role optionalRole= roleRepository.findRoleByName(RoleEnum.TEACHER).orElseThrow(
                        ()-> new EntityNotFoundException("Role not found!!")
                );

                newUser.setEmail(credentialsDto.email());
                newUser.setPassword(passwordEncoder.encode("Teacher1@123"));
                newUser.setTeacher(teacher);
                newUser.setRole(new HashSet<>(Set.of(optionalRole)));

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


                Role parentRole= roleRepository.findRoleByName(RoleEnum.PARENT).orElseThrow(
                        ()->new EntityNotFoundException("Parent role not found!!")
                );
                newUser.setEmail(credentialsDto.email());
                newUser.setPassword(passwordEncoder.encode("Parent1@123"));
                newUser.setParent(parent);
                newUser.setRole(new HashSet<>(Set.of(parentRole)));

                userRepository.save(newUser);
                yield "Parent created!!";
            }

            case STAFF -> {
                yield "Staff created";
            }
        };
    }

    public UserResponseDto createAdmin(CreateCredentialsDto createAdmin){
        Role optionalRole=roleRepository.findRoleByName(RoleEnum.ADMIN).orElseThrow(
                ()-> new EntityNotFoundException("Admin role not found!!")
        );

        var user= new User();
        user.setEmail(createAdmin.email());
        user.setPassword(passwordEncoder.encode(createAdmin.password()));
        user.setRole(new HashSet<>(Set.of(optionalRole)));

        userRepository.save(user);
        return toUserResponseDto(user);

    }

    public UserResponseDto assignAdminRole(AssignAdminDto assignAdminDto){
        Role optionalRole=roleRepository.findRoleByName(RoleEnum.ADMIN).orElseThrow(
                ()-> new EntityNotFoundException("Admin role not found")
        );

        User user= userRepository.findUserByEmail(assignAdminDto.email()).orElseThrow(
                ()-> new EntityNotFoundException("User not found")
        );
//        var user= new User();
        user.setRole(new HashSet<>(Set.of(optionalRole)));
        user.setPassword(passwordEncoder.encode("Admin1@123"));

        userRepository.save(user);
        return toUserResponseDto(user);

    }

    private UserResponseDto toUserResponseDto(User user){
        return new UserResponseDto(
                user.getId(),user.getEmail(),user.getRoleName()
        );
    }
}
