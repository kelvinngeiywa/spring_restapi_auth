package com.kelvin.spring_restapi.entities;

import jakarta.persistence.*;
import jakarta.validation.constraints.Email;
import jakarta.validation.constraints.NotBlank;
import jakarta.validation.constraints.NotNull;
import jakarta.validation.constraints.Pattern;

import java.util.UUID;

@Entity
@Table(name = "parents")
public class Parent {
    @Id
    @GeneratedValue(strategy = GenerationType.UUID)
    private UUID id;
    @NotNull(message="Name is required")
    private String name;
    @NotNull(message="Phone number is required")
    @Pattern(regexp = "^(\\+254|0)[17]\\d{8}$",
            message = "Invalid international phone number format")
    private String phoneNumber;
    @NotBlank(message="Email is required")
    @Email(regexp = "^[A-Za-z0-9._%+-]+@[A-Za-z0-9.-]+\\.[A-Za-z]{2,}$",
            message = "Please enter valid email address")
    private String email;
    @NotBlank(message = "Residence is required")
    private String residence;

    public Parent(){

    }
    public Parent(UUID id,String name,String phoneNumber,String email,String residence){
        this.id=id;
        this.name=name;
        this.email=email;
        this.phoneNumber=phoneNumber;
        this.residence=residence;

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

    public String getPhoneNumber(){
        return this.phoneNumber;
    }

    public void setPhoneNumber(String phoneNumber){
        this.phoneNumber=phoneNumber;
    }

    public String getEmail(){
        return this.email;
    }

    public void setEmail(String email){
        this.email=email;
    }

    public String getResidence(){
        return this.residence;
    }

    public void setResidence(String residence){
        this.residence=residence;
    }
}
