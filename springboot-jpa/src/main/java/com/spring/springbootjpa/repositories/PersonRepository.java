package com.spring.springbootjpa.repositories;

import com.spring.springbootjpa.entities.Person;
import org.springframework.data.repository.CrudRepository;

public interface PersonRepository extends CrudRepository<Person, Long> {

}
