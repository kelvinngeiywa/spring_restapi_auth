package com.kelvin.spring_restapi.entities;

import jakarta.persistence.*;
import org.springframework.security.core.GrantedAuthority;
import org.springframework.security.core.authority.SimpleGrantedAuthority;
import org.springframework.security.core.userdetails.UserDetails;

import java.util.Collection;
import java.util.List;
import java.util.UUID;

@Entity
@Table(name = "users")
public class User implements UserDetails {
    @Id
    @GeneratedValue(strategy = GenerationType.UUID)
    private UUID id;
    private String email;
    private String password;
    @OneToOne(fetch = FetchType.LAZY)
    @JoinColumn(name = "teacherId",unique = true)
    private Teacher teacher;
    @OneToOne(fetch = FetchType.LAZY)
    @JoinColumn(name = "parentId",unique = true)
    private Parent parent;
    @ManyToOne(fetch = FetchType.LAZY)
    @JoinColumn(name = "roleId",referencedColumnName = "id",nullable = false)
    private Role role;

    public User(){

    }

    public User(UUID id,String email,String password,Parent parent,Teacher teacher,Role role){
        this.id=id;
        this.email=email;
        this.password=password;
        this.parent=parent;
        this.teacher=teacher;
        this.role=role;

    }

    @Override
    public Collection<? extends GrantedAuthority> getAuthorities(){
//        SimpleGrantedAuthority authority=new SimpleGrantedAuthority("ROLE_"+ role.getName().toString());
        return List.of();
    }
    @Override
    public String getUsername(){
        return this.email;
    }

    public UUID getId(){
        return this.id;
    }

    public String getEmail(){
        return this.email;
    }

    public void setEmail(String email){
        this.email=email;
    }

    public String getPassword(){
        return this.password;
    }

    public void setPassword(String password){
        this.password=password;
    }

    public Role getRole(){
        return this.role;
    }

    public void setRole(Role role){
        this.role=role;
    }

    public Teacher getTeacher(){
        return this.teacher;
    }

    public void setTeacher(Teacher teacher){
        this.teacher=teacher;
    }
}
