package com.kelvin.spring_restapi.entities;

import jakarta.persistence.*;
import jakarta.validation.constraints.NotBlank;
import jakarta.validation.constraints.NotNull;

import java.util.HashSet;
import java.util.Set;
import java.util.UUID;

@Entity
@Table(name = "subjects")
public class Subject {
    @Id
    @GeneratedValue(strategy = GenerationType.UUID)
    private UUID id;
    @NotNull(message = "Subject name is required")
    @Column(unique = true,nullable = false)
    private String subjectName;
    @NotNull(message = "Subject code is required")
    @Column(unique = true,nullable = false)
    private String subjectCode;
    @ManyToMany(mappedBy = "subjects")
    private Set<Teacher> teachers=new HashSet<>();

    public Subject(){

    }

    public Subject(UUID id,String subjectCode,String subjectName){
        this.id=id;
        this.subjectName=subjectName;
        this.subjectCode=subjectCode;

    }

    public UUID getId(){
        return this.id;
    }

    public void setSubjectName(String subjectName){
        this.subjectName=subjectName;
    }

    public String getSubjectName(){
        return this.subjectName;
    }

    public void setSubjectCode(String subjectCode){
        this.subjectCode=subjectCode;
    }

    public String getSubjectCode(){
        return this.subjectCode;
    }

    public Set<Teacher> getTeachers() {
        return this.teachers;
    }

    public void setTeachers(Set<Teacher> teachers){
        this.teachers=teachers;

    }
}
