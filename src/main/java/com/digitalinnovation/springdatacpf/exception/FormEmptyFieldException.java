package com.digitalinnovation.springdatacpf.exception;

import lombok.Getter;

@Getter
public class FormEmptyFieldException extends NullPointerException{

    private String message;

    public FormEmptyFieldException(String message){
        this.message = message;
    }
}
