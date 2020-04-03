package com.tockys.back.session.service;

import com.tockys.back.session.model.Registered;
import com.tockys.back.session.model.Session;

public interface IRegisteredService {

	Registered createRegistered(Registered registered);
	
	Registered getRegisteredById(long id);
	
	void removeRegistered(Registered registered);
	
	Long countRegisteredBySession(Session session);
}
