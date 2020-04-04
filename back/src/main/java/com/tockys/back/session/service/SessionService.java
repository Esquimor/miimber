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
	public List<Session> getSessionByUserAndDate(User user, OffsetDateTime min, OffsetDateTime max) {
		return sessionRepository.findByOrganizationMembersUserAndStartBetween(user, min, max);
	}

	@Override
	public Session create(Session entity) {
		return sessionRepository.save(entity);
	}

	@Override
	public Session update(Session entity) {
		return sessionRepository.save(entity);
	}

	@Override
	public void delete(Session entity) {
		sessionRepository.delete(entity);
	}

	@Override
	public Session get(Long id) {
		Optional<Session> session = sessionRepository.findById(id);
		if (session.isEmpty()) {
			return null;
		}
		return session.get();
	}

}
