package com.miimber.back.organization.dto.member;

import lombok.Getter;
import lombok.Setter;

@Getter @Setter
public class MemberCreateRequestDTO extends MemberUpdateRequestDTO {

	private Long idOrganization;
	private Long idUser;
}
