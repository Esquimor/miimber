package com.miimber.back.session.dto.attendee;

import java.time.OffsetDateTime;

import lombok.Getter;
import lombok.Setter;

@Getter @Setter
public class AttendeeCreateRequestDTO {

	private long userId;
	private long sessionId;
	private OffsetDateTime dateCheck;
}
