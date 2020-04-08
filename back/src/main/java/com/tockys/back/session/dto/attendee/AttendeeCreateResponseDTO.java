package com.tockys.back.session.dto.attendee;

import java.time.OffsetDateTime;

import com.tockys.back.session.model.Attendee;

public class AttendeeCreateResponseDTO {

	private long id;
	private OffsetDateTime dateChecked;
	private long userId;
	private long sessionId;
	
	public AttendeeCreateResponseDTO(Attendee attendee) {
		this.setId(attendee.getId());
		this.setDateChecked(attendee.getDateCheck());
		this.setUserId(attendee.getUser().getId());
		this.setSessionId(attendee.getSession().getId());
	}

	public long getId() {
		return id;
	}

	public void setId(long id) {
		this.id = id;
	}

	public OffsetDateTime getDateChecked() {
		return dateChecked;
	}

	public void setDateChecked(OffsetDateTime dateChecked) {
		this.dateChecked = dateChecked;
	}

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

	private class UserDTO {
		
		private long id;
		private String firstName;
		private String lastName;
	}
}
