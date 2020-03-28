package com.tockys.back.dto;

import com.tockys.back.model.Member;
import com.tockys.back.model.enums.RoleEnum;

public class MemberCompleteDTO {

	private long id;
	private RoleEnum role;
	private String firstName;
	private String lastName;
	private String email;
	private long userId;
	
	public MemberCompleteDTO(Member member) {
		this.setId(member.getId());
		this.setRole(member.getType());
		this.setFirstName(member.getUser().getFirstName());
		this.setLastName(member.getUser().getLastName());
		this.setEmail(member.getUser().getEmail());
		this.setUserId(member.getUser().getId());
	}

	public long getId() {
		return id;
	}

	public void setId(long id) {
		this.id = id;
	}

	public RoleEnum getRole() {
		return role;
	}

	public void setRole(RoleEnum role) {
		this.role = role;
	}

	public String getFirstName() {
		return firstName;
	}

	public void setFirstName(String firstName) {
		this.firstName = firstName;
	}

	public String getLastName() {
		return lastName;
	}

	public void setLastName(String lastName) {
		this.lastName = lastName;
	}

	public String getEmail() {
		return email;
	}

	public void setEmail(String email) {
		this.email = email;
	}

	public long getUserId() {
		return userId;
	}

	public void setUserId(long userId) {
		this.userId = userId;
	}
}
