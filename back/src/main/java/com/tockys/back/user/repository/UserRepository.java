package com.tockys.back.user.repository;

import org.springframework.data.repository.CrudRepository;

import com.tockys.back.user.model.User;

public interface UserRepository extends CrudRepository<User, Long> {
	User findByEmail(String email);
}
