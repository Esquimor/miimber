package com.tockys.back.dto;

import com.tockys.back.model.enums.RoleEnum;

public class OrganizationDTO {

	private long id;
	private String name;
	
	public OrganizationDTO(long id, String name) {
		this.setId(id);
		this.setName(name);
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
}
