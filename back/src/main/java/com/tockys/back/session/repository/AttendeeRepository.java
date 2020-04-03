package com.tockys.back.session.repository;

import org.springframework.data.repository.CrudRepository;

import com.tockys.back.session.model.Attendee;

public interface AttendeeRepository extends CrudRepository<Attendee, Long>{

}
