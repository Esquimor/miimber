package com.tockys.back.service;

import java.util.List;
import java.util.Optional;

import javax.persistence.EntityNotFoundException;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.tockys.back.model.User;
import com.tockys.back.repository.UserRepository;

@Service
public class UserService implements IUserService {

	@Autowired
	private UserRepository userRepository;

	@Override
	public Optional<User> getUserById(long id) throws EntityNotFoundException {
		return userRepository.findById(id);
	}

	@Override
	public User getUserByUsername(String username) {
		return userRepository.findByUsername(username);
	}

	@Override
	public List<User> getUsers() {
		return (List<User>) userRepository.findAll();
	}

	@Override
	public User createUser(User user) {
		return userRepository.save(user);
	}

	@Override
	public User updateUser(User user) {
		return userRepository.save(user);
	}

	@Override
	public void deleteUserById(long id) {
		userRepository.deleteById(id);
	}
}
