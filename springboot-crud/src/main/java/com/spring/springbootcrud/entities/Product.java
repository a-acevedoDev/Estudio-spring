package com.spring.springbootcrud.entities;

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
    @NotEmpty // Valida que el campo no este vacio.
    @Size(min = 5, max = 45) // Valida el tamaño minimo y maximo del string.
    private String name;
    @Min(500) // Valida intrinsicamente que sea un int y que sea minimo 500.
    @NotNull // Valida que no sea null.
    private Integer price;
    @NotBlank // Valida que no sea null, no sea vacio "" y que no sea empty " " (espacios).
    private String description;
}