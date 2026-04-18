package com.spring.springinyeccion.services;

import com.spring.springinyeccion.models.Product;
import com.spring.springinyeccion.repositories.ProductRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.beans.factory.annotation.Qualifier;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.stereotype.Service;

import java.util.List;
import java.util.stream.Collectors;

@Service
public class ProductServiceImpl implements ProductService {

    //@Qualifier("productRepositoryImpl")
    //@Qualifier nos permite seleccionar que implementacion/inyeccion usar mediante el nombre. Qualifier se prioriza antes que el Primary.
    //El nombre del Qualifier es el nombre de la implementacion partiendo con minuscula, o el nombre que hayamos asignado al repository.
    //Ej:
    //Caso 1, por defecto:
    //@Repository
    //public class ProductRepositoryImpl implements ProductRepository { } el nombre seria "productRepositoryImpl".

    //Caso 2, asignamos nombre al Repository:
    //@Repository("repositorioQualifier")
    //public class ProductRepositoryImpl implements ProductRepository { } el nombre seria "repositorioQualifier".

    @Autowired
    @Qualifier("productJson")
    private ProductRepository repository;
    //Inyeccion de dependencia, provee una instancia Singleton del componente de Spring (@Component)
    //Una buena practica es inyectar interfaces, asi desacoplamos nuestro sistema.

    //----------------------------------------------------------------------//
    //Otra opcion es usar el @Autowired en un metodo setter de la instancia.

    //private ProductRepository repository;

    //@Autowired
    //public void setRepository(ProductRepository repository) {
    //    this.repository = repository;
    //}
    //----------------------------------------------------------------------//
    //Una tercera manera es con un constructor que contenga un componente o bean de Spring. En este caso no haria falta @Autowired.

    //private ProductRepository repository;

    //public ProductServiceImpl(ProductRepository repository) {
    //    this.repository = repository;
    //}
    //----------------------------------------------------------------------//



    @Value("${config.valueTax}")
    private double tax;

    @Override
    public List<Product> findAll () {
        return repository.findAll().stream().map(p -> {
            Double priceTax = p.getPrice() * tax;
            //Product newProduct = new Product(p.getId(), p.getName(), priceTax.longValue()); Una manera de hacerlo sin clone.
            Product newProduct = (Product) p.clone();
            newProduct.setPrice(priceTax.longValue());
            return newProduct;

            //p.setPrice(priceTax.longValue());
            //return p;
        }).collect(Collectors.toList());
    }

    @Override
    public Product findById(Long id) {
        return repository.findById(id);
    }
}
