package com.miimber.back.session.service;

import java.util.Optional;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.miimber.back.session.model.RegisteredSession;
import com.miimber.back.session.model.Session;
import com.miimber.back.session.repository.RegisteredSessionRepository;

@Service
public class RegisteredSessionService implements IRegisteredSessionService {

	@Autowired
	private RegisteredSessionRepository registeredRepository;
	
	@Override
	public Long countRegisteredBySession(Session session) {
		return registeredRepository.countBySession(session);
	}

	@Override
	public RegisteredSession create(RegisteredSession entity) {
		return registeredRepository.save(entity);
	}

	@Override
	public RegisteredSession update(RegisteredSession entity) {
		return registeredRepository.save(entity);
	}

	@Override
	public void delete(RegisteredSession entity) {
		registeredRepository.delete(entity);
	}

	@Override
	public RegisteredSession get(Long id) {
		Optional<RegisteredSession> registered =  registeredRepository.findById(id);
		if (registered.isEmpty()) {
			return null;
		}
		return registered.get();
	}

}
