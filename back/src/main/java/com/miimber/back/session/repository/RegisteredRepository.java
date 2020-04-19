package com.tockys.back.session.repository;

import org.springframework.data.repository.CrudRepository;

import com.tockys.back.session.model.Registered;
import com.tockys.back.session.model.Session;

public interface RegisteredRepository extends CrudRepository<Registered, Long>{

	Long countBySession(Session session);
}
