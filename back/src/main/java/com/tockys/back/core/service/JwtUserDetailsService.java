package com.tockys.back.core.service;

import java.util.ArrayList;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.security.core.userdetails.UserDetails;
import org.springframework.security.core.userdetails.UserDetailsService;
import org.springframework.security.core.userdetails.UsernameNotFoundException;
import org.springframework.security.crypto.password.PasswordEncoder;
import org.springframework.stereotype.Service;

import com.tockys.back.user.dto.UserRegisterDTO;
import com.tockys.back.user.model.User;
import com.tockys.back.user.service.UserService;

@Service
public class JwtUserDetailsService implements UserDetailsService {
    
	@Autowired
	private UserService userService;
	
	@Autowired
	private PasswordEncoder bcryptEncoder;
	
    @Override
	public UserDetails loadUserByUsername(String email) throws UsernameNotFoundException {
    	User user = userService.getUserByEmail(email);
		
		if (user == null) {
			throw new UsernameNotFoundException(String.format("The email %s doesn't exist", email));
		}
		
		return new org.springframework.security.core.userdetails.User(user.getEmail(), user.getPassword(),new ArrayList<>());
	}

	public User save(UserRegisterDTO user) {
		User newUser = new User();
		newUser.setEmail(user.getEmail());
		newUser.setPassword(bcryptEncoder.encode(user.getPassword()));
		return userService.createUser(newUser);
	}

}
