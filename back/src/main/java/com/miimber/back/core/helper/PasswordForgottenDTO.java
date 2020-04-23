package com.miimber.back.core.helper;

import javax.validation.constraints.NotNull;

import lombok.Getter;
import lombok.Setter;

@Getter
@Setter
public class PasswordForgottenDTO {

	@NotNull
	private String email;
}
