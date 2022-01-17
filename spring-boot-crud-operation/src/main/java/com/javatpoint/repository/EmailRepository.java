package com.javatpoint.repository;

import org.springframework.data.repository.CrudRepository;
import com.javatpoint.model.emailsending;

public interface EmailRepository extends CrudRepository<emailsending, Integer> {

	
}
