package com.tockys.back.service;

import java.util.List;
import java.util.Optional;

import com.tockys.back.model.User;

public interface IUserService {
	User getUserById(long id);
	
	List<User> getUsers();
	
	User createUser(User user);
	
	User updateUser(User user);
	
	void updatePasswordUser(User user, String newPassword);
	
	void deleteUserById(long id);
	
	User getUserByEmail(String email);
}
