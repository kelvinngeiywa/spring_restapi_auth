package com.kelvin.spring_restapi.customExceptions;

public class DuplicateEntryException extends RuntimeException{
    public DuplicateEntryException(String message){
        super(message);

    }
}
