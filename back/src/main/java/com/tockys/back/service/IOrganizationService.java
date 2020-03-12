package com.tockys.back.service;

import java.util.List;

import com.tockys.back.model.Organization;
import com.tockys.back.model.User;

public interface IOrganizationService {
	List<Organization> getOrganizationOwnered(User user);
	
	Organization createOrganization(Organization organization);
	
	Organization getOrganizationByName(String name);
	
	Organization getOrganization(Long id);
	
	Organization editOrganization(Organization organization);
}
