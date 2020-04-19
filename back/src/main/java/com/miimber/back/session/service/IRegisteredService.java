package com.miimber.back.session.service;

import com.miimber.back.core.service.TemplateService;
import com.miimber.back.session.model.Registered;
import com.miimber.back.session.model.Session;

public interface IRegisteredService extends TemplateService<Registered> {
	
	Long countRegisteredBySession(Session session);
}
