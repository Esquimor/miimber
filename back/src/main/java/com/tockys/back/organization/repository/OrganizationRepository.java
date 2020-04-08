package com.tockys.back.organization.repository;

import java.util.List;

import org.springframework.data.repository.CrudRepository;

import com.tockys.back.organization.model.Organization;
import com.tockys.back.organization.model.enums.RoleEnum;
import com.tockys.back.user.model.User;

public interface OrganizationRepository extends CrudRepository<Organization, Long> {
	
	List<Organization> findByMembersUserAndMembersRole(User user, RoleEnum owner);
	
	List<Organization> findByMembersUser(User user);
	
	Organization findByName(String name);
}
