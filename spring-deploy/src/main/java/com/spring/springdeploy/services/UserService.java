package com.spring.springdeploy.services;

import com.spring.springdeploy.entities.User;

import java.util.List;

public interface UserService {
    
    List<User> findAll();

    User save(User user);

    boolean existsByUsername(String username);
}
