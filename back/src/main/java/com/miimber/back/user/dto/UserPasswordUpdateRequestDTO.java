package com.miimber.back.user.dto;

import lombok.Getter;
import lombok.Setter;

@Getter @Setter
public class UserPasswordUpdateRequestDTO {

	private String oldPassword;
	private String newPassword;
}
