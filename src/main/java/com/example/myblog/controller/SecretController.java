package com.example.myblog.controller;

import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

@RequestMapping("/secret")
@RestController
public class SecretController {

    @GetMapping
    public String secret() {
        return "secret";
    }

}
