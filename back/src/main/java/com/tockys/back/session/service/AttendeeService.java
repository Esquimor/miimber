package com.tockys.back.service;

import java.util.Optional;

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

	@Override
	public Attendee getAttendeeById(Long id) {
		Optional<Attendee> attendee = attendeeRepository.findById(id);
		if (attendee.isEmpty()) {
			return null;
		}
		return attendee.get();
	}

	@Override
	public void removeAttendee(Attendee attendee) {
		attendeeRepository.delete(attendee);
	}

}
