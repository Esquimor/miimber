package com.tockys.back.service;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.tockys.back.model.Attendee;
import com.tockys.back.repository.AttendeeRepository;

@Service
public class AttendeeService implements IAttendeeService {

	@Autowired
	private AttendeeRepository attendeeRepository;
	
	@Override
	public Attendee createAttendee(Attendee attendee) {
		return attendeeRepository.save(attendee);
	}

}
