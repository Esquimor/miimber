package com.tockys.back.session.service;

import java.time.OffsetDateTime;
import java.util.List;

import com.tockys.back.core.service.TemplateService;
import com.tockys.back.session.model.Session;
import com.tockys.back.user.model.User;

public interface ISessionService extends TemplateService<Session> {
	
	List<Session> getSessionByOrganizationId(long id, OffsetDateTime min, OffsetDateTime max);
	
	List<Session> getSessionByUserAndDate(User user, OffsetDateTime min, OffsetDateTime max);
}
