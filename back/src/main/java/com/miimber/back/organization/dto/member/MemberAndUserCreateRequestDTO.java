package com.miimber.back.organization.dto.member;

import javax.validation.constraints.NotNull;

import lombok.Getter;
import lombok.Setter;

@Getter @Setter
public class MemberAndUserCreateRequestDTO extends MemberUpdateRequestDTO {

	@NotNull
	private Long idOrganization;
	
	@NotNull
	private String email;
	
	@NotNull
	private String firstName;
	
	@NotNull
	private String lastName;
}
