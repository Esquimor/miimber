package com.miimber.back.session.repository;

import org.springframework.data.repository.CrudRepository;

import com.miimber.back.session.model.Attendee;

public interface AttendeeRepository extends CrudRepository<Attendee, Long>{

}
