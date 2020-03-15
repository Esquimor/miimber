package com.tockys.back.service;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.tockys.back.model.TypeSession;
import com.tockys.back.repository.TypeSessionRepository;

@Service
public class TypeSessionService implements ITypeSessionService {

	@Autowired
	private TypeSessionRepository typeSessionRepository;
	
	@Override
	public TypeSession createTypeSession(TypeSession typeSession) {
		return typeSessionRepository.save(typeSession);
	}

}
