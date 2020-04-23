package com.miimber.back.session.dto.session;

import java.time.OffsetDateTime;

import lombok.Getter;
import lombok.Setter;

@Getter @Setter
public class SessionEditRequestDTO {

	private String title;
	private String description;
	private OffsetDateTime start;
	private OffsetDateTime end;
	private long typeSessionId;
	private int limit;
}
