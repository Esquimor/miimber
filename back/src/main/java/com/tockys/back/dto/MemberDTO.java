package com.tockys.back.dto;

import com.tockys.back.model.enums.RoleEnum;

public class MemberDTO {

	private RoleEnum role;
	
	public RoleEnum getRole() {
		return this.role;
	}
	
	public void setRole(RoleEnum role) {
		this.role = role;
	}
}
