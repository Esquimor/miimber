package com.miimber.back.user.repository;

import org.springframework.data.repository.CrudRepository;

import com.miimber.back.user.model.User;

public interface UserRepository extends CrudRepository<User, Long> {
	User findByEmail(String email);
}
