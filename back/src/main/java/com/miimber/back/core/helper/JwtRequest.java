package com.miimber.back.core.helper;

import java.io.Serializable;

import javax.validation.constraints.NotNull;

import lombok.Getter;
import lombok.Setter;

@Getter
@Setter
public class JwtRequest implements Serializable {
	
	/**
	 * 
	 */
	private static final long serialVersionUID = 1L;
	
	@NotNull
	private String email;
	
	@NotNull
	private String password;
	
	public JwtRequest() {
		
	}
	
	public JwtRequest(String email, String password) {
		this.email = email;
		this.password = password;
	}
}
