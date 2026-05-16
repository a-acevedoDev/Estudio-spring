package com.spring.springdeploy.repositories;

import java.util.Optional;

import com.spring.springdeploy.entities.Role;
import org.springframework.data.repository.CrudRepository;

public interface RoleRepository extends CrudRepository<Role, Long> {

    Optional<Role> findByName(String name);
    
}
