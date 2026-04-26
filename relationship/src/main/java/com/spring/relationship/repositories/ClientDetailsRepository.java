package com.spring.relationship.repositories;

import com.spring.relationship.entities.ClientDetails;
import org.springframework.data.repository.CrudRepository;

public interface ClientDetailsRepository extends CrudRepository<ClientDetails, Long> {
}
