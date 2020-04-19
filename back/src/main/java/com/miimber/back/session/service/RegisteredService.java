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
	public Long countRegisteredBySession(Session session) {
		return registeredRepository.countBySession(session);
	}

	@Override
	public Registered create(Registered entity) {
		return registeredRepository.save(entity);
	}

	@Override
	public Registered update(Registered entity) {
		return registeredRepository.save(entity);
	}

	@Override
	public void delete(Registered entity) {
		registeredRepository.delete(entity);
	}

	@Override
	public Registered get(Long id) {
		Optional<Registered> registered =  registeredRepository.findById(id);
		if (registered.isEmpty()) {
			return null;
		}
		return registered.get();
	}

}
