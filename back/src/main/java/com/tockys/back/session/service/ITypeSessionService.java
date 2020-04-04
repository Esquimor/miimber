package com.tockys.back.session.service;

import java.util.List;

import com.tockys.back.core.service.TemplateService;
import com.tockys.back.session.model.TypeSession;

public interface ITypeSessionService extends TemplateService<TypeSession> {
	
	List<TypeSession> getTypeSessionByOrganizationId(long id);
}
