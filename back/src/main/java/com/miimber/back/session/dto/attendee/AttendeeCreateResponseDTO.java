package com.miimber.back.session.dto.attendee;

import java.time.OffsetDateTime;

import com.miimber.back.session.model.Attendee;

import lombok.Getter;
import lombok.Setter;

@Getter @Setter
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
}
