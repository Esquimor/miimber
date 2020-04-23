package com.miimber.back.organization.dto.organization;

import lombok.Getter;
import lombok.Setter;

@Getter @Setter
public class OrganizationCreateRequestDTO {

	private String name;
	private String tokenId;
	private String subscription;
}
