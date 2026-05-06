package com.spring.springbootcrud.entities;

import com.spring.springbootcrud.validation.IsExistDb;
import com.spring.springbootcrud.validation.IsRequired;
import jakarta.persistence.*;
import jakarta.validation.constraints.*;
import lombok.AllArgsConstructor;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;

@Getter
@Setter
@AllArgsConstructor
@NoArgsConstructor
@Entity
@Table(name = "products")
public class Product {
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;
    @IsExistDb
    private String sku;
//    @NotEmpty(message = "{NotEmpty.product.name}") // Valida que el campo no este vacio.
    @IsRequired(message = "{IsRequired.product.name}")
    @Size(min = 5, max = 45) // Valida el tamaño minimo y maximo del string.
    private String name;
    @Min(value = 500, message = "{Min.product.price}") // Valida intrinsicamente que sea un int y que sea minimo 500.
    @NotNull(message = "{NotNull.product.price}") // Valida que no sea null.
    private Integer price;
//    @NotBlank(message = "{NotBlank.product.description}") // Valida que no sea null, no sea vacio "" y que no sea empty " " (espacios).
    @IsRequired
    private String description;
}