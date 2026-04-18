package com.spring.springinyeccion;

import com.spring.springinyeccion.repositories.ProductRepository;
import com.spring.springinyeccion.repositories.ProductRepositoryJson;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;
import org.springframework.context.annotation.PropertySource;

@Configuration
@PropertySource(value = "classpath:values.properties", encoding = "UTF-8")
public class AppConfig {

    @Bean("productJson")
    //Declara un elemento como bean osea un componenete de spring.
    //Inyeccion de dependencia para una clase no perteneciente a spring, esto usando una instancia de esta misma asignadola como @Bean.
    //A @Bean tambien se le puede asignar un nombre logico, como a @Repository.
    ProductRepository productRepositoryJson(){
        return new ProductRepositoryJson();
    }
}
