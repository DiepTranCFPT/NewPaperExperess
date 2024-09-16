package com.experess.news.exception;

import org.springframework.http.HttpStatus;
import org.springframework.web.bind.annotation.ResponseStatus;

@ResponseStatus(HttpStatus.NOT_FOUND)
public class AuthException extends RuntimeException{
    public AuthException(String message){
        super(message);
    }
}

