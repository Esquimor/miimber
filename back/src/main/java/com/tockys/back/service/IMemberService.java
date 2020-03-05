package com.tockys.back.service;

import com.tockys.back.model.Member;
import com.tockys.back.model.Organization;
import com.tockys.back.model.User;

public interface IMemberService {

	Member createMember(Member member);
	
	Member getMemberByOrganizationIdAndByUser(Long id, User user);
}
