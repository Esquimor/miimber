package com.tockys.back.service;

import com.tockys.back.model.Member;
import com.tockys.back.model.Organization;
import com.tockys.back.model.User;

public interface IMemberService {

	Member createMember(Member member);
	
	Member getMemberByOrganizationIdAndByUser(Long id, User user);
	
	Member getMemberByOrganizationAndByUser(Organization organization, User user);
	
	Member updateMember(Member member);
	
	Member getMember(Long id);
	
	void deleteMember(Member member);
}
