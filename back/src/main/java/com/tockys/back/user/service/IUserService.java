package com.tockys.back.user.service;

import java.util.List;
import java.util.Optional;

import com.tockys.back.core.service.TemplateService;
import com.tockys.back.user.model.User;

public interface IUserService extends TemplateService<User> {
	
	List<User> getUsers();
	
	void updatePasswordUser(User user, String newPassword);
	
	void deleteUserById(long id);
	
	User getUserByEmail(String email);
}
