package com.tockys.back.core.controller;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.CrossOrigin;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.RestController;

import com.tockys.back.core.service.JwtUserDetailsService;
import com.tockys.back.user.dto.UserRegisterDTO;

@RestController
@CrossOrigin(origins = "*", allowedHeaders = "*")
public class AuthTestController {
	
	@Autowired
	private JwtUserDetailsService userDetailsService;
	
	@RequestMapping(value = "/test/register", method = RequestMethod.POST)
	public ResponseEntity<?> registerUser(@RequestBody UserRegisterDTO user) throws Exception {
		return ResponseEntity.ok(userDetailsService.save(user));
	}
}
