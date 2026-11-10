package com.kelvin.spring_restapi.entities;

import jakarta.persistence.*;
import jakarta.validation.constraints.Email;
import jakarta.validation.constraints.NotBlank;
import jakarta.validation.constraints.NotNull;
import jakarta.validation.constraints.Past;

import java.time.LocalDate;
import java.util.HashSet;
import java.util.Set;
import java.util.UUID;

@Entity
@Table(name = "teachers")
public class Teacher {
    @Id
    @GeneratedValue(strategy = GenerationType.UUID)
    private UUID id;
    @NotNull(message = "Name is required")
    private String name;
    @NotNull(message = "Phone number is required")
    private String phoneNumber;
    @NotNull(message = "Email is required")
    @Email(regexp = "^[A-Za-z0-9._%+-]+@[A-Za-z0-9.-]+\\.[A-Za-z]{2,}$",
            message = "Please enter a valid email address")
    private String email;
    @NotNull(message = "DOB is required")
    @Past(message = "DOB must be in the past")
    private LocalDate dob;
    @NotBlank(message = "Residence is required")
    private String residence;
    @ManyToMany(fetch = FetchType.LAZY,cascade = {CascadeType.PERSIST,CascadeType.MERGE})
    @JoinTable(
            name = "teacher_subjects",
            joinColumns=@JoinColumn(name = "teacher_id"),
            inverseJoinColumns = @JoinColumn(name = "subject_id")

    )
    private Set<Subject> subjects=new HashSet<>();


    public Teacher(){

    }

    public Teacher(UUID id,String name,String email,String phoneNumber,
                   LocalDate dob,String residence){
        this.name=name;
        this.email=email;
        this.phoneNumber=phoneNumber;
        this.dob=dob;
        this.residence=residence;

    }

    public void addSubject(Subject subject){
        this.subjects.add(subject);
        subject.getTeachers().add(this);
    }

    public void removeSubjects(Subject subject){
        this.subjects.remove(subject);
        subject.getTeachers().remove(this);
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

    public String getEmail(){
        return this.email;
    }

    public void setEmail(String email){
        this.email=email;
    }

    public String getPhoneNumber(){
        return this.phoneNumber;
    }

    public void setPhoneNumber(String phoneNumber){
        this.phoneNumber=phoneNumber;
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

    public void setSubjects(Set<Subject> subjects){
        this.subjects=subjects;
    }

    public Set<Subject> getSubjects(){
        return this.subjects;
    }
}
