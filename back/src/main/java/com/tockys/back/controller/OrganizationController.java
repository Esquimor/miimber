package com.tockys.back.controller;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
import org.springframework.security.core.context.SecurityContextHolder;
import org.springframework.security.core.userdetails.UserDetails;
import org.springframework.web.bind.annotation.CrossOrigin;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.RestController;

import com.tockys.back.helper.Helper;
import com.tockys.back.model.User;
import com.tockys.back.service.OrganizationService;

@RestController
@CrossOrigin(origins = "*", allowedHeaders = "*")
public class OrganizationController {
	
	@Autowired
	private OrganizationService organizationService;
	
	@Autowired
	private Helper helper;

	@RequestMapping(value = "/organization/ownered", method = RequestMethod.GET)
	public ResponseEntity<?> getOrganization() throws Exception {
        UserDetails currentUser = (UserDetails)SecurityContextHolder.getContext().getAuthentication().getPrincipal();
        User user = helper.getUserToken(currentUser);
		return ResponseEntity.ok(organizationService.getOrganizationOwnered(user));
	}
}
