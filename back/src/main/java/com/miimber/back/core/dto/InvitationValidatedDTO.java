package com.miimber.back.core.dto;

import lombok.Getter;
import lombok.Setter;

@Getter
@Setter
public class InvitationValidatedDTO {
	
	private Long id;
	private String token;
	private String firstName;
	private String lastName;
	private String password;
}
