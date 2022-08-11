package com.digitalinnovation.springdatacpf.exception;

import lombok.Getter;

@Getter
public class ZipcodeNotFoundException extends NoSuchFieldException{

    private String message;

    public ZipcodeNotFoundException(String message){
        this.message = message;
    }
}
