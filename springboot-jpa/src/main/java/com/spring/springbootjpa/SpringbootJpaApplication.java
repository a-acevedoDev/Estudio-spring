package com.spring.springbootjpa;

import com.spring.springbootjpa.entities.Person;
import com.spring.springbootjpa.repositories.PersonRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.CommandLineRunner;
import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.context.annotation.PropertySource;

import java.util.List;

@SpringBootApplication
public class SpringbootJpaApplication implements CommandLineRunner {
    // Se añade esta implementacion debido a que es una app de consola, no web.

    @Autowired
    private PersonRepository personRepository;

    public static void main(String[] args) {
        SpringApplication.run(SpringbootJpaApplication.class, args);
    }

    @Override
    public void run(String... args) throws Exception {

        List<Person> persons = (List<Person>) personRepository.findAll();

        persons.stream().forEach( person -> System.out.println(person));
    }
}
