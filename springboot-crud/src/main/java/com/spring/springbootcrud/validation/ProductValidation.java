package com.spring.springbootcrud.validation;

import com.spring.springbootcrud.entities.Product;
import org.springframework.stereotype.Component;
import org.springframework.validation.Errors;
import org.springframework.validation.ValidationUtils;
import org.springframework.validation.Validator;

@Component
public class ProductValidation implements Validator {
    @Override
    public boolean supports(Class<?> clazz) {
        return Product.class.isAssignableFrom(clazz);
    }

    @Override
    public void validate(Object target, Errors errors) {
        Product product = (Product) target;

        // Dos maneras de realizar el control de validaciones, con metodos de ValidationUtils o con un if.
        // INVESTIGAR PORQUE NO TOMO LOS VALORES DE "messages.properties".

        ValidationUtils.rejectIfEmptyOrWhitespace(errors, "name", null,"NotEmpty.product.name");
        //ValidationUtils.rejectIfEmptyOrWhitespace(erros, "description", "NotBlank.product.description");
        if (product.getDescription()==null || product.getDescription().isBlank()) {
            errors.rejectValue("description",null, "NotBlank.product.description");
        }

        if (product.getPrice() == null) {
            errors.rejectValue("price", null,"NotNull.product.price");
        } else if (product.getPrice() < 500) {
            errors.rejectValue("price", null,"Min.product.price");
        }
    }
}
