package com.tockys.back.session.dto;

public class TypeSessionRequestDTO {

	private String name;
	private long organizationId;
	
	public String getName() {
		return name;
	}
	public void setName(String name) {
		this.name = name;
	}
	public long getOrganizationId() {
		return organizationId;
	}
	public void setOrganizationId(long organizationId) {
		this.organizationId = organizationId;
	}
}
