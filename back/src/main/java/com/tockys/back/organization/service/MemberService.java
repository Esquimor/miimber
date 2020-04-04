package com.tockys.back.organization.service;

import java.util.List;
import java.util.Optional;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.tockys.back.organization.model.Member;
import com.tockys.back.organization.model.Organization;
import com.tockys.back.organization.repository.MemberRepository;
import com.tockys.back.user.model.User;

@Service
public class MemberService implements IMemberService {

	@Autowired
	private MemberRepository memberRepository;
	@Override
	public Member getMemberByOrganizationIdAndByUser(Long id, User user) {
		return memberRepository.findOneByOrganizationIdAndUser(id, user);
	}

	@Override
	public List<Member> getMemberByOrganizationId(Long id) {
		return memberRepository.findByOrganizationId(id);
	}

	@Override
	public Member getMemberByOrganizationAndByUser(Organization organization, User user) {
		return memberRepository.findOneByOrganizationAndUser(organization, user);
	}
	
	@Override
	public boolean existsMemberByUserAndOrganization(User user, Organization organization) {
		return existsMemberByUserAndOrganization(user, organization);
	}

	@Override
	public Member create(Member entity) {
		return memberRepository.save(entity);
	}

	@Override
	public Member update(Member entity) {
		return memberRepository.save(entity);
	}

	@Override
	public void delete(Member entity) {
		memberRepository.delete(entity);
	}

	@Override
	public Member get(Long id) {
		Optional<Member> member = memberRepository.findById(id);
		if (member.isEmpty()) {
			return null;
		}
		return member.get();
	}


}
