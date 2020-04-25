package com.miimber.back.organization.dto.organization;

import com.miimber.back.organization.model.Organization;

import lombok.Getter;
import lombok.Setter;

@Getter @Setter
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
}
