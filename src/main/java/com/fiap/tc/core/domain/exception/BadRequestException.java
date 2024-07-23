package com.fiap.tc.core.domain.exception;

import static org.springframework.http.HttpStatus.BAD_REQUEST;

import org.springframework.web.bind.annotation.ResponseStatus;

@ResponseStatus(BAD_REQUEST)
public class BadRequestException extends RuntimeException {

    public BadRequestException(){
        super();
    }

    public BadRequestException(String message){
        super(message);
    }
}
