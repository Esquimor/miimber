package com.tockys.back.organization.service;

import java.util.List;

import com.tockys.back.organization.model.Organization;
import com.tockys.back.user.model.User;

public interface IOrganizationService {
	List<Organization> getOrganizationOwnered(User user);
	
	List<Organization> getOrganizationByUser(User user);
	
	Organization createOrganization(Organization organization);
	
	Organization getOrganizationByName(String name);
	
	Organization getOrganization(Long id);
	
	Organization editOrganization(Organization organization);
	
	void deleteOrganization(Organization organization);
}
