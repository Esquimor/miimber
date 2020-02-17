package com.tockys.back.service;

import java.util.ArrayList;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.security.core.userdetails.UserDetails;
import org.springframework.security.core.userdetails.UserDetailsService;
import org.springframework.security.core.userdetails.UsernameNotFoundException;
import org.springframework.security.crypto.password.PasswordEncoder;
import org.springframework.stereotype.Service;

import com.tockys.back.dto.UserRegisterDTO;
import com.tockys.back.model.User;
import com.tockys.back.repository.UserRepository;

@Service
public class JwtUserDetailsService implements UserDetailsService {
    
	@Autowired
	private UserService userService;
	
	@Autowired
	private PasswordEncoder bcryptEncoder;
	
    @Override
	public UserDetails loadUserByUsername(String username) throws UsernameNotFoundException {
    	User user = userService.getUserByUsername(username);
		
		if (user == null) {
			throw new UsernameNotFoundException(String.format("The username %s doesn't exist", username));
		}
		
		return new org.springframework.security.core.userdetails.User(user.getUsername(), user.getPassword(),new ArrayList<>());
	}

	public User save(UserRegisterDTO user) {
		User newUser = new User();
		newUser.setUsername(user.getUsername());
		newUser.setPassword(bcryptEncoder.encode(user.getPassword()));
		return userService.createUser(newUser);
	}

}
