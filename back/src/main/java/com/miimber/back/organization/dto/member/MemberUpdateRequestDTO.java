package com.miimber.back.organization.dto.member;

import com.miimber.back.organization.model.enums.RoleEnum;

public class MemberUpdateRequestDTO {

	private RoleEnum role = RoleEnum.MEMBER;
	
	public RoleEnum getRole() {
		return this.role;
	}
	
	public void setRole(RoleEnum role) {
		this.role = role;
	}
}
