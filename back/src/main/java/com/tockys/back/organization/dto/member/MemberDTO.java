package com.tockys.back.organization.dto.member;

import com.tockys.back.organization.model.enums.RoleEnum;

public class MemberDTO {

	private RoleEnum role = RoleEnum.MEMBER;
	
	public RoleEnum getRole() {
		return this.role;
	}
	
	public void setRole(RoleEnum role) {
		this.role = role;
	}
}
