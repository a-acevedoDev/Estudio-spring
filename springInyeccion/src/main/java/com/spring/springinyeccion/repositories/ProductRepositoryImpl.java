package com.spring.springinyeccion.repositories;

import com.spring.springinyeccion.models.Product;
import org.springframework.context.annotation.Primary;
import org.springframework.stereotype.Repository;
import org.springframework.web.context.annotation.RequestScope;
import org.springframework.web.context.annotation.SessionScope;

import java.util.Arrays;
import java.util.List;

@Primary
//Indica que un bean debe ser el preferido cuando hay múltiples candidatos del mismo tipo. Solo puede existir un @Primary.
//El problema puede surgir al inyectar una interface y Spring no sepa cual implementacion usar.

//@RequestScope
//@RequestScope cambia el contexto de la aplicacion (por defecto singleton con @Autowired), genera una request que no vive en memoria,
//esta se genera al solicitar una requesty muere al finalizar la request, esto hace que sea inmutable para efectos de la aplicacion.

//@SessionScope
//@SessionScope tambien cambia el contexto, pero solo durante la sesion, guar las peticiones mientras este activa la sesion,
//osea que conserva, modificara, etc mientras exista la sesion, pero al finalizar se destruye el contexto.
@Repository
public class ProductRepositoryImpl implements ProductRepository {
    List<Product> data;

    public ProductRepositoryImpl() {
        this.data = Arrays.asList(
                new Product(1L, "Memoria Corsair 32", 300L),
                new Product(2L, "CPU Intel i9", 850L),
                new Product(3L, "Teclado Razer Mini 60%", 180L),
                new Product(4L, "Motherboard Gigabyte", 490L)
        );
    }

    @Override
    public List<Product> findAll() {
        return data;
    }

    @Override
    public Product findById(Long id) {
        return data.stream().filter(p -> p.getId().equals(id)).findFirst().orElseThrow();
    }
}
