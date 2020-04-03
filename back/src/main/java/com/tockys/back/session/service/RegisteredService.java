package com.tockys.back.session.service;

import java.util.Optional;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.tockys.back.session.model.Registered;
import com.tockys.back.session.model.Session;
import com.tockys.back.session.repository.RegisteredRepository;

@Service
public class RegisteredService implements IRegisteredService {

	@Autowired
	private RegisteredRepository registeredRepository;
	
	@Override
	public Registered createRegistered(Registered registered) {
		return registeredRepository.save(registered);
	}

	@Override
	public Registered getRegisteredById(long id) {
		Optional<Registered> registered =  registeredRepository.findById(id);
		if (registered.isEmpty()) {
			return null;
		}
		return registered.get();
	}

	@Override
	public void removeRegistered(Registered registered) {
		registeredRepository.delete(registered);
	}

	@Override
	public Long countRegisteredBySession(Session session) {
		return registeredRepository.countBySession(session);
	}

}
