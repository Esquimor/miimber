package com.miimber.back.session.repository;

import org.springframework.data.repository.CrudRepository;

import com.miimber.back.session.model.RegisteredSession;
import com.miimber.back.session.model.Session;

public interface RegisteredSessionRepository extends CrudRepository<RegisteredSession, Long>{

	Long countBySession(Session session);
}
