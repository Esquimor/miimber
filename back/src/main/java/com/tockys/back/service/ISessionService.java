package com.tockys.back.service;

import java.util.List;

import com.tockys.back.model.Session;

public interface ISessionService {
	
	List<Session> getSessionByOrganizationId(long id);
	
	Session createSession(Session session);
	
	Session getSessionById(long id);
	
	void deleteSession(Session session);
	
	Session editSession(Session session);
}
