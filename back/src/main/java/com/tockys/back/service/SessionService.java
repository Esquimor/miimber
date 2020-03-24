package com.tockys.back.service;

import java.util.List;

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

}
