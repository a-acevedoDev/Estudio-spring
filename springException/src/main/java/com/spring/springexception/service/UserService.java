package com.spring.springexception.service;

import com.spring.springexception.models.domain.User;

import java.util.List;
import java.util.Optional;

public interface UserService {
    Optional<User> findById(Long id);
    List<User> findAll();

}
