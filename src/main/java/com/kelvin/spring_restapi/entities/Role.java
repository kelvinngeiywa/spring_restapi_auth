package com.kelvin.spring_restapi.entities;

import com.kelvin.spring_restapi.entities.enums.RoleEnum;
import jakarta.persistence.*;
import org.hibernate.annotations.CreationTimestamp;

import java.time.LocalDate;
import java.util.UUID;
@Entity
@Table(name = "roles")
public class Role {
    @Id
    @GeneratedValue(strategy = GenerationType.UUID)
    private UUID id;
    @Column(unique = true,nullable = false)
    @Enumerated(EnumType.STRING)
    private RoleEnum name;
    @Column(nullable = false)
    private String description;
    @CreationTimestamp
    @Column(nullable = false)
    private LocalDate created_at;
    @CreationTimestamp
    @Column
    private LocalDate updated_at;

    public Role(){

    }

    public Role(UUID id,RoleEnum name,String description,LocalDate created_at,
                LocalDate updated_at){
        this.id=id;
        this.name=name;
        this.description=description;
        this.created_at=created_at;
        this.updated_at=updated_at;

    }

    public UUID getId(){
        return this.id;
    }

    public RoleEnum getName(){
        return this.name;
    }

    public void setName(RoleEnum name){
        this.name=name;

    }

    public String getDescription(){
        return this.description;
    }

    public void setDescription(String description){
        this.description=description;
    }

    public LocalDate getCreated_at(){
        return this.created_at;
    }

    public LocalDate getUpdated_at(){
        return this.updated_at;
    }

}
