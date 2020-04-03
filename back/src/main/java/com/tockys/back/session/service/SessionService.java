package com.tockys.back.session.service;

import java.time.OffsetDateTime;
import java.util.List;
import java.util.Optional;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.tockys.back.session.model.Session;
import com.tockys.back.session.repository.SessionRepository;
import com.tockys.back.user.model.User;

@Service
public class SessionService implements ISessionService {

	@Autowired
	private SessionRepository sessionRepository;
	
	@Override
	public List<Session> getSessionByOrganizationId(long id, OffsetDateTime min, OffsetDateTime max) {
		return sessionRepository.findByOrganizationIdAndStartBetween(id, min, max);
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

	@Override
	public List<Session> getSessionByUserAndDate(User user, OffsetDateTime min, OffsetDateTime max) {
		return sessionRepository.findByOrganizationMembersUserAndStartBetween(user, min, max);
	}

}
