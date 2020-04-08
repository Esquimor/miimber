package com.tockys.back.organization.dto.organization;

import com.tockys.back.organization.model.Organization;

public class OrganizationCreateReadUpdateResponseDTO {

	private long id;
	private String name;
	
	public OrganizationCreateReadUpdateResponseDTO(Organization organization) {
		this.setId(organization.getId());
		this.setName(organization.getName());
	}
	
	public OrganizationCreateReadUpdateResponseDTO(long id, String name) {
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
