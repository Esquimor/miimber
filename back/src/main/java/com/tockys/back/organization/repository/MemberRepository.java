package com.tockys.back.repository;

import java.util.List;

import org.springframework.data.repository.CrudRepository;

import com.tockys.back.model.Member;
import com.tockys.back.model.Organization;
import com.tockys.back.model.User;

public interface MemberRepository extends CrudRepository<Member, Long> {
	Member findOneByOrganizationIdAndUser(Long id, User user);
	
	Member findOneByOrganizationAndUser(Organization organization, User user);
	
	List<Member> findByOrganizationId(Long id);
	
	boolean existsMemberByUserAndOrganization(User user, Organization organization);
}
