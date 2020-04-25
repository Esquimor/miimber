package com.miimber.back.session.dto.registered;

import java.time.OffsetDateTime;

import javax.validation.constraints.NotNull;

import lombok.Getter;
import lombok.Setter;

@Getter @Setter
public class RegisteredSessionCreateRequestDTO {

	@NotNull
	private long sessionId;
	@NotNull
	private OffsetDateTime dateRegistered;
}
