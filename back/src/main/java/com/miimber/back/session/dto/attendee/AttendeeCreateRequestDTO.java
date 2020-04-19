package com.tockys.back.session.dto.attendee;

import java.time.OffsetDateTime;

public class AttendeeCreateRequestDTO {

	private long userId;
	private long sessionId;
	private OffsetDateTime dateCheck;
	
	public long getUserId() {
		return userId;
	}
	public void setUserId(long userId) {
		this.userId = userId;
	}
	public long getSessionId() {
		return sessionId;
	}
	public void setSessionId(long sessionId) {
		this.sessionId = sessionId;
	}
	public OffsetDateTime getDateCheck() {
		return dateCheck;
	}
	public void setDateCheck(OffsetDateTime dateCheck) {
		this.dateCheck = dateCheck;
	}
}
