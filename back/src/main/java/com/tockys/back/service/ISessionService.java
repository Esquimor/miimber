package com.tockys.back.service;

import java.util.List;

import com.tockys.back.model.Session;

public interface ISessionService {
	
	List<Session> getSessionByOrganizationId(long id);
}
