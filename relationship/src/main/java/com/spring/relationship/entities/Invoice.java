package com.spring.relationship.entities;

import jakarta.persistence.*;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;
import lombok.ToString;

@Getter
@Setter
@NoArgsConstructor
@ToString
@Entity
@Table(name = "invoices")
public class Invoice {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;
    private String description;
    private Long total;
    @ManyToOne
    @JoinColumn(name = "id_client")
    private Client client;

    public Invoice(String description, Long total) {
        this.description = description;
        this.total = total;
    }
}
