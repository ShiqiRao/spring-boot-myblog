package com.example.myblog.domain;

import lombok.Getter;
import lombok.Setter;
import lombok.experimental.Accessors;

@Accessors(chain = true)
@Setter
@Getter
public class Result<T> {
    private int code;
    private T data;
    private String message;
}
