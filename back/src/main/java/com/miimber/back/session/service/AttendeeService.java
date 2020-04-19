package com.miimber.back.session.service;

import java.util.Optional;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.miimber.back.session.model.Attendee;
import com.miimber.back.session.repository.AttendeeRepository;

@Service
public class AttendeeService implements IAttendeeService {

	@Autowired
	private AttendeeRepository attendeeRepository;

	@Override
	public Attendee create(Attendee entity) {
		return attendeeRepository.save(entity);
	}

	@Override
	public Attendee update(Attendee entity) {
		return attendeeRepository.save(entity);
	}

	@Override
	public void delete(Attendee entity) {
		attendeeRepository.delete(entity);
	}

	@Override
	public Attendee get(Long id) {
		Optional<Attendee> attendee = attendeeRepository.findById(id);
		if (attendee.isEmpty()) {
			return null;
		}
		return attendee.get();
	}

}
