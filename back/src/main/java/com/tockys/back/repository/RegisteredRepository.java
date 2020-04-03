package com.tockys.back.repository;

import org.springframework.data.repository.CrudRepository;

import com.tockys.back.model.Registered;
import com.tockys.back.model.Session;

public interface RegisteredRepository extends CrudRepository<Registered, Long>{

	Long countBySession(Session session);
}
