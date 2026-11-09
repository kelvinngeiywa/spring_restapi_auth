package com.kelvin.spring_restapi.entities.dtos;

import jakarta.persistence.Column;
import jakarta.validation.constraints.NotBlank;
import jakarta.validation.constraints.NotNull;

public class AddSubjectDto {
    @Column(nullable = false)
    @NotBlank(message = "Subject name is required")
    private String subjectName;
    @Column(nullable = false)
    @NotNull(message = "Subject code is required")
    private String subjectCode;

    public AddSubjectDto(){

    }
    public AddSubjectDto(String subjectName,String subjectCode){
        this.subjectName=subjectName;
        this.subjectCode=subjectCode;

    }

    public String getSubjectName(){
        return subjectName;
    }

    public void setSubjectName(String subjectName){
        this.subjectName=subjectName;
    }

    public String getSubjectCode(){
        return subjectCode;
    }

    public void setSubjectCode(String subjectCode){
        this.subjectCode=subjectCode;
    }


}
