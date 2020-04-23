package com.miimber.back.user.dto;

import javax.validation.constraints.NotNull;

import lombok.Getter;
import lombok.Setter;

@Getter @Setter
public class UserEmailUpdateRequestDTO {

	@NotNull
	private String email;
}
