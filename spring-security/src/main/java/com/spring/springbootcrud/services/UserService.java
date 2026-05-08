package com.spring.springbootcrud.services;

import com.spring.springbootcrud.entities.User;

import java.util.List;

public interface UserService {
    List<User> findAll();
    User save(User user);
    boolean existsByUsername(String username);
}
