package com.tockys.back.session.service;

import com.tockys.back.core.service.TemplateService;
import com.tockys.back.session.model.Registered;
import com.tockys.back.session.model.Session;

public interface IRegisteredService extends TemplateService<Registered> {
	
	Long countRegisteredBySession(Session session);
}
