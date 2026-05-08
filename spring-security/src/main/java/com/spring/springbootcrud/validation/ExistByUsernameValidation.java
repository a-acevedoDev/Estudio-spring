package com.spring.springbootcrud.validation;

import com.spring.springbootcrud.services.UserService;
import jakarta.validation.ConstraintValidator;
import jakarta.validation.ConstraintValidatorContext;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Component;

@Component
public class ExistByUsernameValidation implements ConstraintValidator<ExistByUsername,String> {

    @Autowired
    private UserService service;

    @Override
    public boolean isValid(String username, ConstraintValidatorContext context) {
        return !service.existsByUsername(username);
    }
}
