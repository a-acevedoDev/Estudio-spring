package com.spring.projectfactura.models;

import com.fasterxml.jackson.annotation.JsonIgnoreProperties;
import lombok.AllArgsConstructor;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.stereotype.Component;
import org.springframework.web.context.annotation.RequestScope;

@Getter
@Setter
@AllArgsConstructor
@NoArgsConstructor
@Component
@RequestScope
@JsonIgnoreProperties({"targetSource", "advisors"})
public class Client {
    @Value("${client.name}")
    private String name;
    @Value("${client.lasname}")
    private String lastName;
}
