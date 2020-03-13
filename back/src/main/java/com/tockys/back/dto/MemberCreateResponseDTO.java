package com.tockys.back.dto;

import com.tockys.back.model.Member;
import com.tockys.back.model.enums.RoleEnum;

public class MemberCreateResponseDTO extends MemberDTO {
	private Long id;
	private String lastName;
	private String firstName;
	
	public MemberCreateResponseDTO(Member member) {
		this.id = member.getId();
		this.lastName = member.getUser().getLastName();
		this.firstName = member.getUser().getFirstName();
		this.setRole(member.getType());
	}
	
	public MemberCreateResponseDTO(Long id, String lastName, String firstName, RoleEnum role) {
		this.id = id;
		this.lastName = lastName;
		this.firstName = firstName;
		this.setRole(role);
	}
	
	public Long getId() {
		return this.id;
	}
	
	public void setId(Long id) {
		this.id = id;
	}
	
	public String getLastName() {
		return this.lastName;
	}
	
	public void setLastName(String lastName) {
		this.lastName = lastName;
	}
	
	public String getFirstName() {
		return this.firstName;
	}
	
	public void setFirstName(String firstName) {
		this.firstName = firstName;
	}
}