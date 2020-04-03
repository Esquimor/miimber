package com.tockys.back.service;

import java.time.OffsetDateTime;
import java.util.List;

import com.tockys.back.model.Session;
import com.tockys.back.model.User;

public interface ISessionService {
	
	List<Session> getSessionByOrganizationId(long id, OffsetDateTime min, OffsetDateTime max);
	
	List<Session> getSessionByUserAndDate(User user, OffsetDateTime min, OffsetDateTime max);
	
	Session createSession(Session session);
	
	Session getSessionById(long id);
	
	void deleteSession(Session session);
	
	Session editSession(Session session);
}
