package com.spring.projectfactura;

import com.spring.projectfactura.models.Item;
import com.spring.projectfactura.models.Product;
import org.springframework.beans.factory.annotation.Qualifier;
import org.springframework.context.annotation.*;

import java.util.Arrays;
import java.util.List;

@Configuration
@PropertySources(
        @PropertySource(value = "classpath:data.properties", encoding = "UTF-8")
)
public class AppConfig {

    @Bean
    @Primary
    List<Item> itemsInvoice(){
        Product p1= new Product("Camara Sony", 800);
        Product p2= new Product("Saco dormir Doite", 500);
        Product p3= new Product("Bicicleta Bianchi", 1200);
        return Arrays.asList(
                new Item(p1,2),
                new Item(p2,5),
                new Item(p3,1)
        );
    }

    @Bean("default")
    List<Item> itemsInvoiceOffice(){
        Product p1= new Product("Monitor Asus 14", 800);
        Product p2= new Product("Notebook Razer", 2000);
        Product p3= new Product("Corchetera", 100);
        Product p4= new Product("Escritorio oficina", 1200);
        return Arrays.asList(
                new Item(p1,2),
                new Item(p2,3),
                new Item(p3,5),
                new Item(p4, 2)
        );
    }
}
