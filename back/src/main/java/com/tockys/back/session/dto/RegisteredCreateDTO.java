package com.tockys.back.session.dto;

import java.time.OffsetDateTime;

public class RegisteredCreateDTO {

	private long sessionId;
	private OffsetDateTime dateRegistered;

	public long getSessionId() {
		return sessionId;
	}
	public void setSessionId(long sessionId) {
		this.sessionId = sessionId;
	}
	public OffsetDateTime getDateRegistered() {
		return dateRegistered;
	}
	public void setDateRegistered(OffsetDateTime dateRegistered) {
		this.dateRegistered = dateRegistered;
	}
	
}
