package com.tockys.back.service;

import java.util.List;
import java.util.Optional;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.tockys.back.model.Session;
import com.tockys.back.repository.SessionRepository;

@Service
public class SessionService implements ISessionService {

	@Autowired
	private SessionRepository sessionRepository;
	
	@Override
	public List<Session> getSessionByOrganizationId(long id) {
		return sessionRepository.findByOrganizationId(id);
	}

	@Override
	public Session createSession(Session session) {
		return sessionRepository.save(session);
	}

	@Override
	public Session getSessionById(long id) {
		Optional<Session> session = sessionRepository.findById(id);
		if (session.isEmpty()) {
			return null;
		}
		return session.get();
	}

	@Override
	public void deleteSession(Session session) {
		sessionRepository.delete(session);
	}

	@Override
	public Session editSession(Session session) {
		return sessionRepository.save(session);
	}

}
