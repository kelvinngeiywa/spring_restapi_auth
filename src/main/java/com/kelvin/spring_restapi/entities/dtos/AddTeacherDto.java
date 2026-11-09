package com.kelvin.spring_restapi.entities.dtos;

import jakarta.validation.constraints.Email;
import jakarta.validation.constraints.NotBlank;
import jakarta.validation.constraints.NotNull;
import jakarta.validation.constraints.Past;

import java.time.LocalDate;

public class AddTeacherDto {
    @NotNull(message = "Name is required")
    private String name;
    @NotNull(message = "Phone number is required")
    private String phoneNumber;
    @NotNull(message = "Email is required")
    @Email(regexp = "^[A-Za-z0-9._%+-]+@[A-Za-z0-9.-]+\\.[A-Za-z]{2,}$",
           message = "Please enter valid email address")
    private String email;
    @NotNull(message = "DOB is required")
    @Past(message = "DOB must be in the past")
    private LocalDate dob;
    @NotBlank(message = "Residence is required")
    private String residence;

    public AddTeacherDto(){

    }

    public AddTeacherDto(String name,String email,String phoneNumber,String residence,LocalDate dob){
        this.name=name;
        this.email=email;
        this.phoneNumber=phoneNumber;
        this.residence=residence;
        this.dob=dob;

    }

    public String getName(){
        return this.name;
    }

    public String getEmail(){
        return this.email;
    }

    public String getPhoneNumber(){
        return this.phoneNumber;
    }

    public String getResidence(){
        return this.residence;
    }

    public LocalDate getDob(){
        return this.dob;
    }
}
