package com.example.myblog.controller;

import lombok.extern.slf4j.Slf4j;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

@RequestMapping("/log")
@Slf4j
@RestController
public class LogController {

    @GetMapping
    public String justShowSomeLog() {
        log.trace("TRACE Message.");
        log.debug("DEBUG Message.");
        log.info("INFO Message.");
        log.warn("WARN Message.");
        log.error("ERROR Message.");
        return "Bro,go check your console.";
    }

}
