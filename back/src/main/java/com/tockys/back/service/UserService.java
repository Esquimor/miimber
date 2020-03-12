package com.tockys.back.service;

import java.util.List;
import java.util.Optional;

import javax.persistence.EntityNotFoundException;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.security.crypto.password.PasswordEncoder;
import org.springframework.stereotype.Service;

import com.tockys.back.model.User;
import com.tockys.back.repository.UserRepository;

@Service
public class UserService implements IUserService {

	@Autowired
	private UserRepository userRepository;
	
	@Autowired
	private PasswordEncoder bcryptEncoder;

	@Override
	public User getUserById(long id) throws EntityNotFoundException {
		Optional<User> user = userRepository.findById(id);
		if (user.isEmpty()) {
			return null;
		}
		return user.get();
	}

	@Override
	public User getUserByEmail(String email) {
		return userRepository.findByEmail(email);
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

	@Override
	public void updatePasswordUser(User user, String newPassword) {
		user.setPassword(bcryptEncoder.encode(newPassword));
		userRepository.save(user);
	}
}
