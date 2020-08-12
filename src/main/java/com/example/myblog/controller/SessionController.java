package com.example.myblog.controller;

import com.example.myblog.domain.SessionQuery;
import org.springframework.http.HttpStatus;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;
import org.springframework.web.server.ResponseStatusException;

import javax.servlet.http.Cookie;
import javax.servlet.http.HttpServletResponse;
import java.util.Objects;

@RestController
@RequestMapping("/session")
public class SessionController {

    @PostMapping
    public String login(@RequestBody SessionQuery sessionQuery, HttpServletResponse response) {
        if (authenticate(sessionQuery)) {
            certificate(response);
            return "success";
        }
        //登陆失败返回401错误
        throw new ResponseStatusException(HttpStatus.UNAUTHORIZED);
    }

    private boolean authenticate(SessionQuery sessionQuery) {
        //简单的验证逻辑，仅作演示
        return Objects.equals(sessionQuery.getUsername(), "admin") &&
                Objects.equals(sessionQuery.getPassword(), "password");
    }

    private void certificate(HttpServletResponse response) {
        //将登陆凭证以cookie的形式返回给客户端
        Cookie credential = new Cookie("sessionId", "test-info");
        response.addCookie(credential);
    }

}
