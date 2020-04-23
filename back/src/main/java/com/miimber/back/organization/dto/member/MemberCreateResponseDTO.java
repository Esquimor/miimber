package com.miimber.back.organization.dto.member;

import com.miimber.back.organization.model.Member;
import com.miimber.back.organization.model.enums.RoleEnum;

import lombok.Getter;
import lombok.Setter;

@Getter @Setter
public class MemberCreateResponseDTO extends MemberUpdateRequestDTO {
	private Long id;
	private String lastName;
	private String firstName;
	
	public MemberCreateResponseDTO(Member member) {
		this.id = member.getId();
		this.lastName = member.getUser().getLastName();
		this.firstName = member.getUser().getFirstName();
		this.setRole(member.getRole());
	}
	
	public MemberCreateResponseDTO(Long id, String lastName, String firstName, RoleEnum role) {
		this.id = id;
		this.lastName = lastName;
		this.firstName = firstName;
		this.setRole(role);
	}
}
