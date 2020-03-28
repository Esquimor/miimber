package com.tockys.back.repository;

import java.util.List;

import org.springframework.data.repository.CrudRepository;

import com.tockys.back.model.TypeSession;

public interface TypeSessionRepository extends CrudRepository<TypeSession, Long> {

	List<TypeSession> findByOrganizationId(long id);
}
