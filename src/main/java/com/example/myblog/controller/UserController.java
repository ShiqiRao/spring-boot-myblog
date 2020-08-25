package com.example.myblog.controller;

import com.example.myblog.entity.User;
import com.example.myblog.repository.UserRepository;
import org.springframework.http.HttpStatus;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;
import org.springframework.web.server.ResponseStatusException;

import java.util.Map;

@RestController
@RequestMapping("/api/user")
public class UserController {

    private final UserRepository userRepository;
    private final String MULTI = "METHOD=multi";
    private final String IN_MAP = "METHOD=in-map";

    public UserController(UserRepository userRepository) {
        this.userRepository = userRepository;
    }

    @GetMapping("/")
    public Iterable<User> findAll() {
        return userRepository.findAll();
    }

    @GetMapping("/{login:[\\D]+}")
    public User findOne(@PathVariable String login) {
        //login 只匹配非数字的参数
        //单个参数绑定单个入参
        User result = userRepository.findByLogin(login);
        if (result == null) {
            throw new ResponseStatusException(HttpStatus.NOT_FOUND, "This user does not exit");
        }
        return result;
    }

    @GetMapping(value = "/{firstName}/{lastName}", params = MULTI)
    public User findOne(@PathVariable String firstName, @PathVariable String lastName) {
        //多个参数绑定多个入参
        User result = userRepository.findByFirstNameAndLastName(firstName, lastName);
        if (result == null) {
            throw new ResponseStatusException(HttpStatus.NOT_FOUND, "This user does not exit");
        }
        return result;
    }

    @GetMapping(value = "/{firstName}/{lastName}", params = IN_MAP)
    public User findOne(@PathVariable Map<String, String> paramMap) {
        //多个参数绑定单个Map
        User result = userRepository.findByFirstNameAndLastName(paramMap.get("firstName"), paramMap.get("lastName"));
        if (result == null) {
            throw new ResponseStatusException(HttpStatus.NOT_FOUND, "This user does not exit");
        }
        return result;
    }
}
