package com.kelvin.spring_restapi.bootstrap;

import com.kelvin.spring_restapi.entities.Role;
import com.kelvin.spring_restapi.entities.enums.RoleEnum;
import com.kelvin.spring_restapi.repository.RoleRepository;
import org.springframework.context.ApplicationListener;
import org.springframework.context.event.ContextRefreshedEvent;
import org.springframework.stereotype.Component;

import java.util.Arrays;
import java.util.Map;
import java.util.Optional;

@Component
public class RoleSeeder implements ApplicationListener<ContextRefreshedEvent> {
    private final RoleRepository roleRepository;

    public RoleSeeder(RoleRepository roleRepository){
        this.roleRepository=roleRepository;

    }

    @Override
    public void onApplicationEvent(ContextRefreshedEvent contextRefreshedEvent){
        this.loadRoles();

    }

    private void loadRoles(){
        RoleEnum[] roleNames=new RoleEnum[]{
                RoleEnum.PARENT,RoleEnum.TEACHER,RoleEnum.ADMIN,RoleEnum.PRINCIPAL,RoleEnum.SUPER_ADMIN
        };
        Map<RoleEnum,String> roleEnumStringMap=Map.of(
                RoleEnum.PARENT,"Parent role",
                RoleEnum.TEACHER,"Teacher role",
                RoleEnum.ADMIN,"Admin role",
                RoleEnum.PRINCIPAL,"Principal role",
                RoleEnum.SUPER_ADMIN,"Super admin role"
        );

        Arrays.stream(roleNames).forEach((roleName)->{
            Optional<Role> role=roleRepository.findRoleByName(roleName);

            role.ifPresentOrElse(System.out::println,()->{
                Role createRole= new Role();

                createRole.setName(roleName);
                createRole.setDescription(roleEnumStringMap.get(roleName));

                roleRepository.save(createRole);

            });
        });
    }
}
