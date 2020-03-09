package com.tockys.back.service;

import java.util.List;
import java.util.Optional;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.tockys.back.model.Organization;
import com.tockys.back.model.User;
import com.tockys.back.model.enums.RoleEnum;
import com.tockys.back.repository.OrganizationRepository;

@Service
public class OrganizationService implements IOrganizationService {

	@Autowired
	private OrganizationRepository organizationRepository;
	
	@Override
	public List<Organization> getOrganizationOwnered(User user) {
		return organizationRepository.findByMembersUserAndMembersRole(user, RoleEnum.OWNER);
	}

	@Override
	public Organization createOrganization(Organization organization) {
		return organizationRepository.save(organization);
	}

	@Override
	public Organization getOrganizationByName(String name) {
		return organizationRepository.findByName(name);
	}

	@Override
	public Organization getOrganization(Long id) {
		Optional<Organization> organization = organizationRepository.findById(id);
		if (organization.isEmpty()) {
			return null;
		}
		return organization.get();
	}
}
