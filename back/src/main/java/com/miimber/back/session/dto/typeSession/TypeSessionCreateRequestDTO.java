package com.miimber.back.session.dto.typeSession;

import lombok.Getter;
import lombok.Setter;

@Getter @Setter
public class TypeSessionCreateRequestDTO {

	private String name;
	private long organizationId;
}
