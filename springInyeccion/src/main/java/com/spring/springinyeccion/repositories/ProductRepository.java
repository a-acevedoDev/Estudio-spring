package com.spring.springinyeccion.repositories;

import com.spring.springinyeccion.models.Product;

import java.util.List;

public interface ProductRepository {
    List<Product> findAll();
    Product findById(Long id);
}