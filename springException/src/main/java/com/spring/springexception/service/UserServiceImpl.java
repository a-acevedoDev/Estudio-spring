package com.spring.springexception.service;

import com.spring.springexception.models.domain.Role;
import com.spring.springexception.models.domain.User;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.ArrayList;
import java.util.Arrays;
import java.util.List;
import java.util.Optional;

@Service
public class UserServiceImpl implements UserService{

    @Autowired
    private List<User> userList;

    @Override
    public Optional<User> findById(Long id) {
        Optional<User> user = userList.stream()
                .filter(user1 -> user1.getId().equals(id))
                .findFirst();
        return user;
    }

    @Override
    public List<User> findAll() {
        return userList;
    }
}
