package com.miimber.back.user.dto;

import lombok.Getter;
import lombok.Setter;

@Getter @Setter
public class UserUpdateRequestDTO {

	private long id;
	private String email;
	private String firstName;
	private String lastName;
}
