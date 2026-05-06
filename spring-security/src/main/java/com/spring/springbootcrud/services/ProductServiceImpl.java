package com.spring.springbootcrud.services;

import com.spring.springbootcrud.entities.Product;
import com.spring.springbootcrud.respositories.ProductRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import java.util.List;
import java.util.Optional;

@Service
public class ProductServiceImpl implements ProductService{

    @Autowired
    private ProductRepository productRepository;

    @Transactional(readOnly = true)
    @Override
    public List<Product> findAll() {
        return (List<Product>) productRepository.findAll();
    }

    @Transactional(readOnly = true)
    @Override
    public Optional<Product> findById(Long id) {
        return productRepository.findById(id);
    }

    @Transactional
    @Override
    public Product save(Product product) {
        return productRepository.save(product);
    }

    @Transactional
    @Override
    public Optional<Product> update(Long id, Product product) {
        Optional<Product> optionalProduct = productRepository.findById(id);
        if(optionalProduct.isPresent()){
            Product productDb = optionalProduct.orElseThrow();
            productDb.setSku(product.getSku());
            productDb.setName(product.getName());
            productDb.setPrice(product.getPrice());
            productDb.setDescription(product.getDescription());
            return Optional.of(productRepository.save(productDb));
        }
        return optionalProduct;
    }

    @Transactional
    @Override
    public Optional<Product> delete(Long id) {
        Optional<Product> optionalProduct = productRepository.findById(id);
        optionalProduct.ifPresent(productDb -> {
            productRepository.delete(productDb);
        });
        return optionalProduct;
    }

    @Transactional(readOnly = true)
    @Override
    public boolean existsBySku(String sku) {
        return productRepository.existsBySku(sku);
    }
}
