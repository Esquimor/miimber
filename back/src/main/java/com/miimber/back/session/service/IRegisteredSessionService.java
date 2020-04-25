package com.miimber.back.session.service;

import com.miimber.back.core.service.TemplateService;
import com.miimber.back.session.model.RegisteredSession;
import com.miimber.back.session.model.Session;

public interface IRegisteredSessionService extends TemplateService<RegisteredSession> {
	
	Long countRegisteredBySession(Session session);
}
