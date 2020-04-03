package com.tockys.back.session.service;

import java.time.OffsetDateTime;
import java.util.List;

import com.tockys.back.session.model.Session;
import com.tockys.back.user.model.User;

public interface ISessionService {
	
	List<Session> getSessionByOrganizationId(long id, OffsetDateTime min, OffsetDateTime max);
	
	List<Session> getSessionByUserAndDate(User user, OffsetDateTime min, OffsetDateTime max);
	
	Session createSession(Session session);
	
	Session getSessionById(long id);
	
	void deleteSession(Session session);
	
	Session editSession(Session session);
}
