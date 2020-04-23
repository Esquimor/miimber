package com.miimber.back.organization.dto.member;

import lombok.Getter;
import lombok.Setter;

@Getter @Setter
public class MemberAndUserCreateRequestDTO extends MemberUpdateRequestDTO {

	private Long idOrganization;
	private String email;
	private String firstName;
	private String lastName;
}
