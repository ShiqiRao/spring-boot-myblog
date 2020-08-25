package com.example.myblog.repository;

import com.example.myblog.entity.User;
import org.springframework.data.repository.CrudRepository;

public interface UserRepository extends CrudRepository<User, Long> {
    User findByLogin(String login);

    User findByFirstNameAndLastName(String firstName, String lastName);
}
