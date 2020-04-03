package com.tockys.back.session.service;

import com.tockys.back.session.model.Attendee;

public interface IAttendeeService {

	Attendee createAttendee(Attendee attendee);
	
	Attendee getAttendeeById(Long id);
	
	void removeAttendee(Attendee attendee);
}
