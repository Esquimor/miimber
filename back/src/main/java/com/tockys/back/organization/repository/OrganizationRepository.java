package com.tockys.back.repository;

import java.util.List;

import org.springframework.data.repository.CrudRepository;

import com.tockys.back.model.Organization;
import com.tockys.back.model.User;
import com.tockys.back.model.enums.RoleEnum;

public interface OrganizationRepository extends CrudRepository<Organization, Long> {
	
	List<Organization> findByMembersUserAndMembersRole(User user, RoleEnum owner);
	
	List<Organization> findByMembersUser(User user);
	
	Organization findByName(String name);
}
