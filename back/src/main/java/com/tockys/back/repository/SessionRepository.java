package com.tockys.back.repository;

import java.util.List;

import org.springframework.data.repository.CrudRepository;

import com.tockys.back.model.Session;

public interface SessionRepository extends CrudRepository<Session, Long>  {

	List<Session> findByOrganizationId(long id);
}
