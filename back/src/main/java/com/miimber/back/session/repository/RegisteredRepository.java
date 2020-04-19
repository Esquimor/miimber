package com.miimber.back.session.repository;

import org.springframework.data.repository.CrudRepository;

import com.miimber.back.session.model.Registered;
import com.miimber.back.session.model.Session;

public interface RegisteredRepository extends CrudRepository<Registered, Long>{

	Long countBySession(Session session);
}
