package com.spring.relationship.entities;

import jakarta.persistence.*;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;
import lombok.ToString;

import java.util.Objects;

@Getter
@Setter
@NoArgsConstructor
@Entity
@Table(name = "invoices")
public class Invoice {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;
    private String description;
    private Long total;
    @ManyToOne
    @JoinColumn(name = "client_id")
    private Client client;

    public Invoice(String description, Long total) {
        this.description = description;
        this.total = total;
    }

    // Eliminamos el client del toString Invoice, esto debido que al estar en ambos toString (client-invoice) se genera un loop infinito.
    @Override
    public String toString() {
        return "Invoice{" +
                "total=" + total +
                ", description='" + description + '\'' +
                ", id=" + id +
                '}';
    }

    @Override
    public boolean equals(Object o) {
        if (o == null || getClass() != o.getClass()) return false;
        Invoice invoice = (Invoice) o;
        return Objects.equals(id, invoice.id) && Objects.equals(description, invoice.description) && Objects.equals(total, invoice.total);
    }

    @Override
    public int hashCode() {
        return Objects.hash(id, description, total);
    }
}
