package com.tockys.back.session.repository;

import java.util.List;

import org.springframework.data.repository.CrudRepository;

import com.tockys.back.session.model.TypeSession;

public interface TypeSessionRepository extends CrudRepository<TypeSession, Long> {

	List<TypeSession> findByOrganizationId(long id);
}
