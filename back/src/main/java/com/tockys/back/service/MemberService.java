package com.tockys.back.service;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.tockys.back.model.Member;
import com.tockys.back.repository.MemberRepository;

@Service
public class MemberService implements IMemberService {

	@Autowired
	private MemberRepository memberRepository;
	
	@Override
	public Member createMember(Member member) {
		return memberRepository.save(member);
	}

}
