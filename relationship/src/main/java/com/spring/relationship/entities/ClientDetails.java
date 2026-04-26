package com.spring.relationship.entities;

import jakarta.persistence.*;
import lombok.AllArgsConstructor;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;

@Getter
@Setter
@NoArgsConstructor
@AllArgsConstructor
@Entity
@Table(name = "client_details")
public class ClientDetails {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;
    private boolean premium;
    private Integer points;

    public ClientDetails(boolean premium, Integer points) {
        this.premium = premium;
        this.points = points;
    }

    // Cuidado con generar relaciones siclicas.
    @Override
    public String toString() {
        return "ClientDetails{" +
                "id=" + id +
                ", premium=" + premium +
                ", points=" + points +
                '}';
    }
}
