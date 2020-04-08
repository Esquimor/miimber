package com.tockys.back.user.service;

import java.util.List;
import java.util.Optional;

import javax.persistence.EntityNotFoundException;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.security.crypto.password.PasswordEncoder;
import org.springframework.stereotype.Service;

import com.tockys.back.user.model.User;
import com.tockys.back.user.repository.UserRepository;

@Service
public class UserService implements IUserService {

	@Autowired
	private UserRepository userRepository;
	
	@Autowired
	private PasswordEncoder bcryptEncoder;

	@Override
	public User getUserByEmail(String email) {
		return userRepository.findByEmail(email);
	}

	@Override
	public List<User> getUsers() {
		return (List<User>) userRepository.findAll();
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

	@Override
	public User create(User entity) {
		return userRepository.save(entity);
	}

	@Override
	public User update(User entity) {
		return userRepository.save(entity);
	}

	@Override
	public void delete(User entity) {
		userRepository.delete(entity);
	}

	@Override
	public User get(Long id) {
		Optional<User> user = userRepository.findById(id);
		if (user.isEmpty()) {
			return null;
		}
		return user.get();
	}
}
