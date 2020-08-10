package com.example.myblog.controller;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

@RequestMapping("/log")
@RestController
public class LogController {

    Logger logger = LoggerFactory.getLogger(LogController.class);

    @GetMapping
    public String justShowSomeLog() {
        logger.trace("TRACE Message.");
        logger.debug("DEBUG Message.");
        logger.info("INFO Message.");
        logger.warn("WARN Message.");
        logger.error("ERROR Message.");
        return "Bro,go check your console.";
    }

}
