package com.spring.springexception;

import com.spring.springexception.models.domain.User;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;

import java.util.ArrayList;
import java.util.List;

@Configuration
public class AppConfig {

    @Bean
    List<User> userList() {
        List<User> users = new ArrayList<>();
        users.add(new User(1L,"Alexander", "Acevedo"));
        users.add(new User(2L,"Pedro", "Fuentes"));
        users.add(new User(3L,"Nico", "Guerra"));
        users.add(new User(4L,"Emma", "Acevedo"));
        users.add(new User(5L,"Yesenia", "Rozas"));
        return users;
    }
}
