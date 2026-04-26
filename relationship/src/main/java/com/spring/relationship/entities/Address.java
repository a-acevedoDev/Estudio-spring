package com.spring.relationship.entities;

import jakarta.persistence.*;
import lombok.*;

import java.util.Objects;

@Getter
@Setter
@NoArgsConstructor
@Entity
@Table(name = "addresses")
public class Address {
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;
    private String street;
    private Integer number;

    public Address(String street, Integer number) {
        this.street = street;
        this.number = number;
    }

    // Necesario para el criterio de busqueda o comparacion. Ayuda en caso de que cambie la referencia post persistir.

    @Override
    public boolean equals(Object o) {
        if (o == null || getClass() != o.getClass()) return false;
        Address address = (Address) o;
        return Objects.equals(street, address.street) &&
                Objects.equals(number, address.number);
    }

    @Override
    public int hashCode() {
        return Objects.hash(street, number);
    }

    @Override
    public String toString() {
        return "Address{" +
                "id=" + id +
                ", street='" + street + '\'' +
                ", number=" + number +
                '}';
    }
}
