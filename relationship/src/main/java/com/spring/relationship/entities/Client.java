package com.spring.relationship.entities;

import jakarta.persistence.*;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;
import lombok.ToString;

import java.util.ArrayList;
import java.util.HashSet;
import java.util.List;
import java.util.Set;

@Getter
@Setter
@NoArgsConstructor
@Entity
@Table(name = "clients")
public class Client {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;
    private String name;
    private String lastname;
    //  Le da un nombre y hace que la FK este en la tabla relacionada (addresses)
    //@JoinColumn(name = "client_id")
    // Crea una tabla intermedia personalizable
    @JoinTable(
            name = "tbl_client_to_directions",
            joinColumns = @JoinColumn(name = "id_cliente"),
            inverseJoinColumns = @JoinColumn(name = "id_direcciones"),
            uniqueConstraints = @UniqueConstraint(columnNames = {"id_direcciones"})
    )
    // cascade genera la relacion de cascada padre-hijo, orphanRemoval true elimina a los registros huerfanos del padre, fetch eager carga todos los hijos siempre.
    @OneToMany(cascade = CascadeType.ALL, orphanRemoval = true)
    private Set<Address> addresses = new HashSet<>();

    @OneToMany(cascade = CascadeType.ALL, orphanRemoval = true, mappedBy = "client")
    private List<Invoice> invoices = new ArrayList<>();

    @OneToOne
    @JoinColumn(name = "id_client_details")
    private ClientDetails clientDetails;

    public Client(String name, String lastname) {
        this.name = name;
        this.lastname = lastname;
    }

    // Metodo que toma los invoices y los agrega al client.
    public Client addInvoices(Invoice invoice) {
        invoices.add(invoice);
        invoice.setClient(this);
        return this;
    }

    public void removeInvoice(Invoice invoice) {
        this.getInvoices().remove(invoice);
        invoice.setClient(null);
    }

    @Override
    public String toString() {
        return "Client{" +
                "id=" + id +
                ", name='" + name + '\'' +
                ", lastname='" + lastname + '\'' +
                ", addresses=" + addresses +
                ", invoices=" + invoices +
                ", client details=" + clientDetails +
                '}';
    }
}
