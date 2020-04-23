package com.miimber.back.core.helper;

import javax.validation.constraints.NotNull;

import lombok.Getter;
import lombok.Setter;

@Getter
@Setter
public class ResetPasswordDTO {

	@NotNull
	private Long id;
	
	@NotNull
	private String token;
	
	@NotNull
	private String password;
}
