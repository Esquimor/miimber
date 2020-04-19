package com.tockys.back.session.service;

import java.util.Optional;

import org.springframework.beans.factory.annotation.Autowired;

import com.tockys.back.session.model.TemplateSession;
import com.tockys.back.session.repository.TemplateSessionRepository;

public class TemplateSessionService implements ITemplateSessionService {

	@Autowired
	private TemplateSessionRepository templateSessionRepository;
	
	@Override
	public TemplateSession create(TemplateSession entity) {
		return templateSessionRepository.save(entity);
	}

	@Override
	public TemplateSession update(TemplateSession entity) {
		return templateSessionRepository.save(entity);
	}

	@Override
	public void delete(TemplateSession entity) {
		templateSessionRepository.delete(entity);
	}

	@Override
	public TemplateSession get(Long id) {
		Optional<TemplateSession> templateSession = templateSessionRepository.findById(id);
		if (templateSession.isEmpty())	{
			return null;
		}
		return templateSession.get();
	}

}
