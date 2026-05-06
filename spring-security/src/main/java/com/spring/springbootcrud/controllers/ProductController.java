package com.spring.springbootcrud.controllers;

import com.spring.springbootcrud.entities.Product;
import com.spring.springbootcrud.services.ProductService;
import jakarta.validation.Valid;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.validation.BindingResult;
import org.springframework.web.bind.annotation.*;

import java.util.HashMap;
import java.util.List;
import java.util.Map;
import java.util.Optional;

@RestController
@RequestMapping("/api/products")
public class ProductController {

    @Autowired
    private ProductService productService;

//    @Autowired
//    private ProductValidation validation;

    @GetMapping
    public List<Product> list() {
        return productService.findAll();
    }

    @GetMapping("/{id}")
    public ResponseEntity<?> view(@PathVariable Long id) {
        Optional<Product> optionalProduct = productService.findById(id);
        if(optionalProduct.isPresent()) {
            return ResponseEntity.ok(optionalProduct.orElseThrow());
        }
        return ResponseEntity.notFound().build();
    }

    @PostMapping
    // El <?> es necesario porque puede devolver un Product o un Map con los errores.
    public ResponseEntity<?> create(@Valid @RequestBody Product product, BindingResult bindingResult) {
        // Validacion de ProductValidation.
        //validation.validate(product, bindingResult);
        if(bindingResult.hasFieldErrors()) {
            return validation(bindingResult);
        }
        return ResponseEntity.status(HttpStatus.CREATED).body(productService.save(product));
    }

    // El binding result siempre debe ir despues del request body en los argumentos.
    @PutMapping("/{id}")
    public ResponseEntity<?> update(@Valid @RequestBody Product product, BindingResult bindingResult, @PathVariable Long id) {

        if(bindingResult.hasFieldErrors()) {
            return validation(bindingResult);
        }

        Optional<Product> optionalProduct = productService.update(id, product);
        if (optionalProduct.isPresent()) {
            return ResponseEntity.status(HttpStatus.OK).body(optionalProduct.orElseThrow());
        }
        return ResponseEntity.notFound().build();
    }

    @DeleteMapping("/{id}")
    public ResponseEntity<?> delete(@PathVariable Long id) {
        Optional<Product> optionalProduct = productService.delete(id);
        if(optionalProduct.isPresent()) {
            return ResponseEntity.ok(optionalProduct.orElseThrow());
        }
        return ResponseEntity.notFound().build();
    }

    // Metodo para validacion, devuelve un json con los campos que contengan un error y su error.
    private ResponseEntity<?> validation(BindingResult bindingResult) {
        Map<String, String> errors = new HashMap<>();
        bindingResult.getFieldErrors().forEach(fieldError -> {
            errors.put(fieldError.getField(), "El campo " + fieldError.getField() + " " + fieldError.getDefaultMessage());
        });
        return ResponseEntity.badRequest().body(errors);
    }
}
