package com.spring.springbootjpa.entities;

import jakarta.persistence.*;
import lombok.AllArgsConstructor;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;

@Getter
@Setter
@NoArgsConstructor
@AllArgsConstructor
@Entity // Define la clase como una entidad en la base de datos.
@Table(name = "persons") // Opcional, personaliza la entidad, por defecto el nombre es el de la clase.
public class Person {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;
    private String name;
    private String lastname;
    @Column(name = "programming_language") // Personaliza la columna/atributo, en este caso el nombre por convencion de base de datos (snakecase).
    private String programmingLanguage;

    public Person(String name, String lastname) {
        this.name = name;
        this.lastname = lastname;
    }

    @Override
    public String toString() {
        return "{" +
                "id=" + id +
                ", name='" + name + '\'' +
                ", lastname='" + lastname + '\'' +
                ", programmingLanguage='" + programmingLanguage + '\'' +
                '}';
    }
}
