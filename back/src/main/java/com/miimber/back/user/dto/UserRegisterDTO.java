package com.miimber.back.user.dto;

import lombok.Getter;
import lombok.Setter;

@Getter @Setter
public class UserRegisterDTO {
	
	private String email;
	private String password;
	private String lastName;
	private String firstName;
}
