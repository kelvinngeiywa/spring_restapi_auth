package com.kelvin.spring_restapi.entities;

import com.kelvin.spring_restapi.entities.enums.GradeStreamEnum;
import jakarta.persistence.*;
import jakarta.validation.constraints.NotBlank;
import jakarta.validation.constraints.NotNull;


import java.time.LocalDate;
import java.util.UUID;

@Entity
@Table(name = "students")
public class Student {
    @Id
    @GeneratedValue(strategy = GenerationType.UUID)
    private UUID id;
    @Column(nullable = false)
    private String name;
    @Column(unique = true,nullable = false)
    @NotBlank(message = "Registration number is required!!")
    private String regNumber;
    @Column(nullable = false)
    private int grade;
    @Column(nullable = false)
    private GradeStreamEnum stream;
    @NotNull(message="DOB is required")
    private LocalDate dob;
    @NotNull(message="Student residence is required")
    private String residence;
    @ManyToOne(fetch = FetchType.LAZY, optional = false)
    @JoinColumn(name = "parent_id",referencedColumnName = "id",nullable = false)
    private Parent parent;

    public Student(){

    }

    public Student(UUID id,String name,String regNumber,int grade,
                   GradeStreamEnum stream,LocalDate dob,String residence,Parent parent ){
        this.id=id;
        this.name=name;
        this.regNumber=regNumber;
        this.grade=grade;
        this.stream=stream;
        this.dob=dob;
        this.residence=residence;
        this.parent=parent;
    }

    public UUID getId(){
        return this.id;
    }

    public String getName(){
        return this.name;
    }

    public void setName(String name){
        this.name=name;
    }

    public String getRegNumber(){
        return this.regNumber;
    }

    public void setRegNumber(String regNumber){
        this.regNumber=regNumber;
    }

    public int getGrade(){
        return this.grade;

    }

    public void setGrade(int grade){
        this.grade=grade;
    }

    public GradeStreamEnum getStream(){
        return this.stream;
    }

    public void setStream(GradeStreamEnum stream){
        this.stream=stream;
    }

    public LocalDate getDob(){
        return this.dob;
    }

    public void setDob(LocalDate dob){
        this.dob=dob;
    }

    public String getResidence(){
        return this.residence;
    }

    public void setResidence(String residence){
        this.residence=residence;
    }

    public Parent getParent(){
        return this.parent;
    }

    public void setParent(Parent parent){
        this.parent=parent;

    }


}
