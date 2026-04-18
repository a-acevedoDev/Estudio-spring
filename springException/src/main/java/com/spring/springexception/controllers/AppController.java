package com.spring.springexception.controllers;

import com.spring.springexception.Exceptions.UserNotFoundException;
import com.spring.springexception.models.domain.User;
import com.spring.springexception.service.UserService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import java.util.Optional;

@RestController
@RequestMapping("/app")
public class AppController {

    @Autowired
    private UserService userService;

    @GetMapping()
    public String index() {
        //ArithmeticException
        //int value = 100/0;

        //NumberFormatException
        //int value = Integer.parseInt("10HOLA");
        return "Ok 200";
    }

    @GetMapping("/show/{id}")
    public User show(@PathVariable(name = "id") Long id) {
        User user = userService.findById(id).orElseThrow(() -> new UserNotFoundException("Usuario no existe."));
        return user;
    }
}
