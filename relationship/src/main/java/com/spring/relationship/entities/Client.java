package com.spring.relationship.entities;

import jakarta.persistence.*;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;
import lombok.ToString;

import java.util.ArrayList;
import java.util.List;

@Getter
@Setter
@NoArgsConstructor
@ToString
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
    // cascade genera la relacion de cascada padre-hijo, orphanRemoval elimina a los registros huerfanos al elimnar al padre.
    @OneToMany(cascade = CascadeType.ALL, orphanRemoval = true)
    private List<Address> addresses = new ArrayList<>();

    public Client(String name, String lastname) {
        this.name = name;
        this.lastname = lastname;
    }
}
