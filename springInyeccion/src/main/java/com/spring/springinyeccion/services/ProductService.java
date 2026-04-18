package com.spring.springinyeccion.services;

import com.spring.springinyeccion.models.Product;

import java.util.List;

public interface ProductService {
    List<Product> findAll();
    Product findById(Long id);
}
