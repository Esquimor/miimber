package com.tockys.back.dto;

import com.tockys.back.model.Member;
import com.tockys.back.model.Organization;
import com.tockys.back.model.enums.RoleEnum;

public class OrganizationCompleteDTO {
	
	private long id;
	private String name;
	private UserDTO user;

	public OrganizationCompleteDTO(Organization organization, Member member) {
		this.id = organization.getId();
		this.name = organization.getName();
		if (member == null) {
			this.setUser(null);
		} else {
			this.setUser(new UserDTO(member));
		}
	}

	public long getId() {
		return id;
	}

	public void setId(long id) {
		this.id = id;
	}

	public String getName() {
		return name;
	}

	public void setName(String name) {
		this.name = name;
	}
	
	public UserDTO getUser() {
		return user;
	}

	public void setUser(UserDTO user) {
		this.user = user;
	}

	private class UserDTO {
		
		private long id;
		private RoleEnum role;
		
		public UserDTO(Member member) {
			this.setId(member.getId());
			this.setRole(member.getType());
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
	}
}
