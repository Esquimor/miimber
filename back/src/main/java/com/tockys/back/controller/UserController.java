package com.tockys.back.controller;

import java.util.Optional;

import org.modelmapper.ModelMapper;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.CrossOrigin;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.RestController;

import com.tockys.back.dto.UserDTO;
import com.tockys.back.model.User;
import com.tockys.back.service.UserService;

@RestController
@CrossOrigin
public class UserController {
	
	@Autowired
	private UserService userService;
	
    @Autowired
    private ModelMapper modelMapper;
	
	@RequestMapping(value = "/user/", method = RequestMethod.GET)
	public ResponseEntity<?> getUsers() {
		return ResponseEntity.ok(userService.getUsers());
	}
	
	@RequestMapping(value = "/user/{id}", method = RequestMethod.GET)
	public ResponseEntity<?> getUser(@PathVariable Long id) {
		return ResponseEntity.ok(userService.getUserById(id));
	}
	
	@RequestMapping(value = "/user/{id}", method = RequestMethod.PUT)
	public ResponseEntity<?> putUser(@RequestBody UserDTO userDto, @PathVariable Long id) {
		User user = convertToEntity(userDto, id);
		if (user == null) {
			return new ResponseEntity(HttpStatus.NOT_FOUND);
		}
		return ResponseEntity.ok(userService.updateUser(user));
	}
	
	private UserDTO convertToDto(User user) {
		UserDTO userDto = modelMapper.map(user, UserDTO.class);
		return userDto;
	}
	
	private User convertToEntity(UserDTO userDto, long id) {
		Optional<User> userOptional = userService.getUserById(id);
		if (userOptional.isEmpty()) {
			return null;
		}
		User user = userOptional.get();
		user.setFirstName(userDto.getFirstName());
		user.setLastName(userDto.getLastName());
		user.setEmail(userDto.getEmail());
		return user;
	}
}
