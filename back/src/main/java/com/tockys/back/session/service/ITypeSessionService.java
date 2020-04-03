package com.tockys.back.session.service;

import java.util.List;

import com.tockys.back.session.model.TypeSession;

public interface ITypeSessionService {

	TypeSession createTypeSession(TypeSession typeSession);
	
	List<TypeSession> getTypeSessionByOrganizationId(long id);
	
	TypeSession getTypeSessionById(long id);
	
	TypeSession editTypeSession(TypeSession typeSession);
	
	void deleteTypeSession(TypeSession typeSession);
}
