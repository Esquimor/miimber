package com.tockys.back.service;

import com.tockys.back.model.Attendee;

public interface IAttendeeService {

	Attendee createAttendee(Attendee attendee);
	
	Attendee getAttendeeById(Long id);
	
	void removeAttendee(Attendee attendee);
}
