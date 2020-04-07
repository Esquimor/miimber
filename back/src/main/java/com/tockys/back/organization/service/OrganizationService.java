package com.tockys.back.organization.service;

import java.util.List;
import java.util.Optional;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.tockys.back.organization.model.Organization;
import com.tockys.back.organization.model.enums.RoleEnum;
import com.tockys.back.organization.repository.OrganizationRepository;
import com.tockys.back.user.model.User;

@Service
public class OrganizationService implements IOrganizationService {

	@Autowired
	private OrganizationRepository organizationRepository;
	
	@Override
	public List<Organization> getOrganizationOwnered(User user) {
		return organizationRepository.findByMembersUserAndMembersRole(user, RoleEnum.OWNER);
	}

	@Override
	public List<Organization> getOrganizationByUser(User user) {
		return organizationRepository.findByMembersUser(user);
	}


	@Override
	public Organization getOrganizationByName(String name) {
		return organizationRepository.findByName(name);
	}

	@Override
	public Organization create(Organization entity) {
		return organizationRepository.save(entity);
	}

	@Override
	public Organization update(Organization entity) {
		return organizationRepository.save(entity);
	}

	@Override
	public void delete(Organization entity) {
		organizationRepository.delete(entity);
	}

	@Override
	public Organization get(Long id) {
		Optional<Organization> organization = organizationRepository.findById(id);
		if (organization.isEmpty()) {
			return null;
		}
		return organization.get();
	}
}