package com.tockys.back.service;

import com.tockys.back.model.Registered;
import com.tockys.back.model.Session;

public interface IRegisteredService {

	Registered createRegistered(Registered registered);
	
	Registered getRegisteredById(long id);
	
	void removeRegistered(Registered registered);
	
	Long countRegisteredBySession(Session session);
}
