package com.miimber.back.session.dto.registered;

import java.time.OffsetDateTime;

import lombok.Getter;
import lombok.Setter;

@Getter @Setter
public class RegisteredCreateRequestDTO {

	private long sessionId;
	private OffsetDateTime dateRegistered;
}
