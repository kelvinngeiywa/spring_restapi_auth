package com.kelvin.spring_restapi.entities.dtos;

import jdk.jfr.DataAmount;
import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;
import org.springframework.http.HttpStatus;

import java.io.Serializable;

@Data
@NoArgsConstructor
@AllArgsConstructor
public class ResponseDto<T> implements Serializable {
    private Integer status;
    private String message;
    private T data;

    public static <T> ResponseDto<T> success(T data){
        return new ResponseDto<>(200,"Success",data);
    }

    public static<T> ResponseDto<T> error(Integer status,String message){
        return new ResponseDto<>(status,message,null);
    }
}
