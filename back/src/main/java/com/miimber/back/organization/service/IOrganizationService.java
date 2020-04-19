package com.tockys.back.organization.service;

import java.util.List;

import com.tockys.back.core.service.TemplateService;
import com.tockys.back.organization.model.Organization;
import com.tockys.back.user.model.User;

public interface IOrganizationService extends TemplateService<Organization> {
	List<Organization> getOrganizationEditable(User user);
	
	List<Organization> getOrganizationByUser(User user);
	
	Organization getOrganizationByName(String name);
}
