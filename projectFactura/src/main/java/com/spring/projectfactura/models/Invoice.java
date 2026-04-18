package com.spring.projectfactura.models;

import com.fasterxml.jackson.annotation.JsonIgnoreProperties;
import jakarta.annotation.PostConstruct;
import jakarta.annotation.PreDestroy;
import lombok.AllArgsConstructor;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.beans.factory.annotation.Qualifier;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.stereotype.Component;
import org.springframework.web.context.annotation.RequestScope;

import java.util.List;

@Getter
@Setter
@AllArgsConstructor
@NoArgsConstructor
@Component
@RequestScope
@JsonIgnoreProperties({"targetSource", "advisors"})
public class Invoice {

    @Autowired
    private Client client;

    @Value("${invoice.description.office}")
    private String description;

    @Autowired
    @Qualifier("default")
    private List<Item> items;

    @PostConstruct
    //Se ejecuta despues de crear (instaciar) e inyectar dependecias (@Autowired, @Value, etc).
    public void init() {
        System.out.println("Creando el componente de la factura.");
        client.setName(client.getName().concat(" Matias"));
        description = description.concat(" del cliente: ").concat(client.getName());
        //concat crea un nuevo String en memoria, por eso se asigna a la inyeccion existente "description".
    }

    @PreDestroy
    //Se ejecuta antes de finalizar el componente bean.
    public void destroy() {
        System.out.println("Destruyendo el componente bean factura.");
    }

    //El API REST/API jackson transforma nuestros metodos get en el json, no nuestros con nuestros atributos.

    public int getTotal() {
        return items.stream().mapToInt(Item::getImport).sum();
    }
}
