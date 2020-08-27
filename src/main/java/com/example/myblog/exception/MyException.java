package com.example.myblog.exception;

import org.springframework.http.HttpStatus;
import org.springframework.web.bind.annotation.ResponseStatus;

@ResponseStatus(value = HttpStatus.NOT_FOUND)
public class MyException extends Exception{

    public MyException() {
    }

    public MyException(String message) {
        super(message);
    }
}
