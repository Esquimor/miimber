package com.tockys.back.session.service;

import java.util.List;
import java.util.Optional;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.tockys.back.session.model.TypeSession;
import com.tockys.back.session.repository.TypeSessionRepository;

@Service
public class TypeSessionService implements ITypeSessionService {

	@Autowired
	private TypeSessionRepository typeSessionRepository;
	
	@Override
	public TypeSession createTypeSession(TypeSession typeSession) {
		return typeSessionRepository.save(typeSession);
	}

	@Override
	public List<TypeSession> getTypeSessionByOrganizationId(long id) {
		return typeSessionRepository.findByOrganizationId(id);
	}

	@Override
	public TypeSession getTypeSessionById(long id) {
		Optional<TypeSession> typeSession = typeSessionRepository.findById(id);
		if (typeSession.isEmpty()) {
			return null;
		}
		return typeSession.get();
	}

	@Override
	public TypeSession editTypeSession(TypeSession typeSession) {
		return typeSessionRepository.save(typeSession);
	}

	@Override
	public void deleteTypeSession(TypeSession typeSession) {
		typeSessionRepository.delete(typeSession);
	}

}
