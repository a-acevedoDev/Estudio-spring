package com.spring.springbootcrud.validation;

import jakarta.validation.Constraint;
import jakarta.validation.Payload;

import java.lang.annotation.ElementType;
import java.lang.annotation.Retention;
import java.lang.annotation.RetentionPolicy;
import java.lang.annotation.Target;

@Target(ElementType.FIELD)
@Retention(RetentionPolicy.RUNTIME)
@Constraint(validatedBy = ExistByUsernameValidation.class)
public @interface ExistByUsername {
    String message() default "Nombre de usuario ya existe en la base de datos";

    Class<?>[] groups() default { };

    Class<? extends Payload>[] payload() default { };
}
