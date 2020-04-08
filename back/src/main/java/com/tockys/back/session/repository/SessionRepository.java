package com.tockys.back.session.repository;

import java.time.OffsetDateTime;
import java.util.List;

import org.springframework.data.repository.CrudRepository;

import com.tockys.back.session.model.Session;
import com.tockys.back.user.model.User;

public interface SessionRepository extends CrudRepository<Session, Long>  {

	List<Session> findByOrganizationIdAndStartBetween(long id, OffsetDateTime min, OffsetDateTime max);
	
	List<Session> findByOrganizationMembersUserAndStartBetween(User user, OffsetDateTime min, OffsetDateTime max);
}
