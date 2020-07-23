package com.example.myblog.controller;

import com.example.myblog.entity.User;
import com.example.myblog.repository.UserRepository;
import org.springframework.http.HttpStatus;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;
import org.springframework.web.server.ResponseStatusException;

@RestController
@RequestMapping("/api/user")
public class UserController {

    private final UserRepository userRepository;

    public UserController(UserRepository userRepository) {
        this.userRepository = userRepository;
    }

    @GetMapping("/")
    public Iterable<User> findAll() {
        return userRepository.findAll();
    }

    @GetMapping("/{login}")
    public User findOne(@PathVariable String login) {
        User result = userRepository.findByLogin(login);
        if (result == null) {
            throw new ResponseStatusException(HttpStatus.NOT_FOUND, "This user does not exit");
        }
        return result;
    }
}
