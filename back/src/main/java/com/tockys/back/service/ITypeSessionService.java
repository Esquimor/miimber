package com.tockys.back.service;

import java.util.List;

import com.tockys.back.model.TypeSession;

public interface ITypeSessionService {

	TypeSession createTypeSession(TypeSession typeSession);
	
	List<TypeSession> getTypeSessionByOrganizationId(long id);
	
	TypeSession getTypeSessionById(long id);
	
	TypeSession editTypeSession(TypeSession typeSession);
	
	void deleteTypeSession(TypeSession typeSession);
}
