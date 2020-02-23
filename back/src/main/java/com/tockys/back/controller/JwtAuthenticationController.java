package com.tockys.back.controller;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
import org.springframework.security.authentication.AuthenticationManager;
import org.springframework.security.authentication.BadCredentialsException;
import org.springframework.security.authentication.DisabledException;
import org.springframework.security.authentication.UsernamePasswordAuthenticationToken;
import org.springframework.security.core.context.SecurityContextHolder;
import org.springframework.security.core.userdetails.UserDetails;
import org.springframework.web.bind.annotation.CrossOrigin;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.RestController;

import com.tockys.back.config.JwtTokenUtil;
import com.tockys.back.dto.UserRegisterDTO;
import com.tockys.back.helper.JwtRequest;
import com.tockys.back.helper.JwtResponse;
import com.tockys.back.service.JwtUserDetailsService;
import com.tockys.back.service.UserService;

@RestController
@CrossOrigin
public class JwtAuthenticationController {

	@Autowired
	private AuthenticationManager authenticationManager;
	
	@Autowired
	private JwtTokenUtil jwtTokenUtil;
	
	@Autowired
	private JwtUserDetailsService userDetailsService;
	
	@Autowired
	private UserService userService;
	
	@RequestMapping(value = "/login", method = RequestMethod.POST)
	public ResponseEntity<?> createAuthenticationToken(@RequestBody JwtRequest authenticationRequest) throws Exception {
		authenticate(authenticationRequest.getEmail(), authenticationRequest.getPassword());
		
		final UserDetails userDetails = userDetailsService
				.loadUserByUsername(authenticationRequest.getEmail());
		final String token = jwtTokenUtil.generateToken(userDetails);
		
		return ResponseEntity.ok(new JwtResponse(token));
	}
	
	@RequestMapping(value = "/register", method = RequestMethod.POST)
	public ResponseEntity<?> registerUser(@RequestBody UserRegisterDTO user) throws Exception {
		return ResponseEntity.ok(userDetailsService.save(user));
	}
	
	@RequestMapping(value = "/me", method = RequestMethod.GET)
	public ResponseEntity<?> me() throws Exception {
		UserDetails userDetails = (UserDetails) SecurityContextHolder.getContext().getAuthentication()
                .getPrincipal();
		return ResponseEntity.ok(userService.getUserByEmail(userDetails.getUsername()));
	}
	
	private void authenticate(String username, String password) throws Exception {
		try {
			authenticationManager.authenticate(new UsernamePasswordAuthenticationToken(username, password));
		} catch (DisabledException e) {
			throw new Exception("USER_DISABLED", e);
		} catch (BadCredentialsException e) {
			throw new Exception("INVALID_CREDENTIALS", e);
		}
	}
}


