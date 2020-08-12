package com.example.myblog.domain;

import lombok.Getter;
import lombok.Setter;
import lombok.experimental.Accessors;

@Accessors(chain = true)
@Setter
@Getter
public class SessionQuery {

    private String username;

    private String password;

}
