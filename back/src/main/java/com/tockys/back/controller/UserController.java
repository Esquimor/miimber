package com.tockys.back.controller;

import java.util.Optional;

import org.modelmapper.ModelMapper;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.security.core.context.SecurityContextHolder;
import org.springframework.security.core.userdetails.UserDetails;
import org.springframework.security.crypto.password.PasswordEncoder;
import org.springframework.web.bind.annotation.CrossOrigin;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.RestController;

import com.tockys.back.dto.UserDTO;
import com.tockys.back.dto.UserPasswordDTO;
import com.tockys.back.model.User;
import com.tockys.back.service.UserService;

@RestController
@CrossOrigin(origins = "*", allowedHeaders = "*")
public class UserController {
	
	@Autowired
	private UserService userService;
	
    @Autowired
    private ModelMapper modelMapper;
    
	@Autowired
	private PasswordEncoder bcryptEncoder;
	
	@RequestMapping(value = "/me", method = RequestMethod.GET)
	public ResponseEntity<?> me() throws Exception {
        UserDetails currentUser = (UserDetails)SecurityContextHolder.getContext().getAuthentication().getPrincipal();
		return ResponseEntity.ok(convertToDto(getUserToken(currentUser)));
	}
	
	@RequestMapping(value = "/user/{id}", method = RequestMethod.PUT)
	public ResponseEntity<?> putUser(@RequestBody UserDTO userDto, @PathVariable Long id) {
		User user = convertToEntity(userDto, id);
		if (user == null) {
			return new ResponseEntity(HttpStatus.NOT_FOUND);
		}
        User tokenUser = getUserToken((UserDetails)SecurityContextHolder.getContext().getAuthentication().getPrincipal());
		if (user.getId() != tokenUser.getId()) {
			return new ResponseEntity(HttpStatus.UNAUTHORIZED);
		}
		return ResponseEntity.ok(convertToDto(userService.updateUser(user)));
	}

	@RequestMapping(value = "/user/{id}/password", method = RequestMethod.PUT)
	public ResponseEntity<?> putPasswordUser(@RequestBody UserPasswordDTO userDTO, @PathVariable Long id) {
        User tokenUser = getUserToken((UserDetails)SecurityContextHolder.getContext().getAuthentication().getPrincipal());
		if (id != tokenUser.getId()) {
			return new ResponseEntity(HttpStatus.UNAUTHORIZED);
		}
		if (!bcryptEncoder.matches(userDTO.getOldPassword(), tokenUser.getPassword())) {
			return new ResponseEntity(HttpStatus.NOT_ACCEPTABLE);
		}
		userService.updatePasswordUser(tokenUser, userDTO.getNewPassword());
		return new ResponseEntity(HttpStatus.OK);
	}
	
	private User getUserToken(UserDetails currentUser) {
		return userService.getUserByEmail(currentUser.getUsername());
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
		return user;
	}
}
