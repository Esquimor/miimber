package com.tockys.back.organization.service;

import java.util.List;

import com.tockys.back.organization.model.Member;
import com.tockys.back.organization.model.Organization;
import com.tockys.back.user.model.User;

public interface IMemberService {

	Member createMember(Member member);
	
	Member getMemberByOrganizationIdAndByUser(Long id, User user);
	
	List<Member> getMemberByOrganizationId(Long id);
	
	Member getMemberByOrganizationAndByUser(Organization organization, User user);
	
	Member updateMember(Member member);
	
	Member getMember(Long id);
	
	void deleteMember(Member member);
	
	boolean existsMemberByUserAndOrganization(User user, Organization organization);
}
