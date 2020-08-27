package com.example.myblog.controller;

import com.example.myblog.exception.MyException;
import lombok.extern.slf4j.Slf4j;
import org.springframework.web.bind.annotation.ExceptionHandler;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

@Slf4j
@RestController
@RequestMapping("/exception")
public class ExceptionController {

    @GetMapping("/mine")
    public String throwAnMyException() throws Exception {
        throw new MyException("Demonstration");
    }

    @GetMapping
    public String throwAnException() throws Exception {
        throw new  Exception("Demonstration");
    }

    @ExceptionHandler({MyException.class})
    public void handleException(MyException e) {
        //这里可以任意编写异常处理逻辑
        log.info("got an exception" + e.toString());
    }

}
