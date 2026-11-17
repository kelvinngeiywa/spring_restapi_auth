package com.kelvin.spring_restapi.entities;

import jakarta.persistence.*;
import org.hibernate.engine.internal.Cascade;
import org.springframework.security.core.GrantedAuthority;
import org.springframework.security.core.authority.SimpleGrantedAuthority;
import org.springframework.security.core.userdetails.UserDetails;

import java.util.*;
import java.util.stream.Collectors;

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
    @ManyToMany(fetch = FetchType.EAGER)
    @JoinTable(
            name = "user_roles",
            joinColumns = @JoinColumn(name = "user_id"),
            inverseJoinColumns = @JoinColumn(name = "role_id")
    )
    private Set<Role> roles = new HashSet<>();

    public User(){

    }

    public User(UUID id,String email,String password,Parent parent,Teacher teacher,Set<Role> roles){
        this.id=id;
        this.email=email;
        this.password=password;
        this.parent=parent;
        this.teacher=teacher;
        this.roles=roles;

    }

    @Override
    public Collection<? extends GrantedAuthority> getAuthorities(){
//        SimpleGrantedAuthority authority=new SimpleGrantedAuthority("ROLE_"+ role.getName().toString());

        return roles.stream().map(r-> new SimpleGrantedAuthority("ROLE_" + r.getName())).toList();
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

    public Set<Role> getRole(){
        return this.roles;
    }

    public void setRole(Set<Role> role){
        this.roles=role;
    }

    public Teacher getTeacher(){
        return this.teacher;
    }

    public void setTeacher(Teacher teacher){
        this.teacher=teacher;
    }

    public Parent getParent(){
        return this.parent;
    }

    public void setParent(Parent parent){
        this.parent=parent;
    }

    public Set<String> getRoleName(){
        return this.roles.stream().map(
                role -> role.getName().name()
        ).collect(Collectors.toSet());
    }
}
