package com.spring.springbootcrud.validation;

import jakarta.validation.ConstraintValidator;
import jakarta.validation.ConstraintValidatorContext;
import org.springframework.util.StringUtils;

public class RequiredValidation implements ConstraintValidator<IsRequired, String> {
    @Override
    public boolean isValid(String value, ConstraintValidatorContext context) {
        // Dos maneras de validar un String.
//        return value != null && !value.isBlank();
        return StringUtils.hasText(value);
    }
}
