package com.tockys.back.repository;

import java.util.Optional;

import org.springframework.data.repository.CrudRepository;

import com.tockys.back.model.User;

public interface UserRepository extends CrudRepository<User, Long> {
	User findByEmail(String email);
}
