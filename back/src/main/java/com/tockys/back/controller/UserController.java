package com.tockys.back.controller;

import java.util.ArrayList;
import java.util.List;
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

import com.tockys.back.dto.OrganizationDTO;
import com.tockys.back.dto.UserDTO;
import com.tockys.back.dto.UserPasswordDTO;
import com.tockys.back.helper.Helper;
import com.tockys.back.model.Member;
import com.tockys.back.model.Organization;
import com.tockys.back.model.User;
import com.tockys.back.service.MemberService;
import com.tockys.back.service.OrganizationService;
import com.tockys.back.service.UserService;

@RestController
@CrossOrigin(origins = "*", allowedHeaders = "*")
public class UserController {
	
	@Autowired
	private UserService userService;
	
	@Autowired
	private MemberService memberService;
	
	@Autowired
	private OrganizationService organizationService;
	
    @Autowired
    private ModelMapper modelMapper;
    
	@Autowired
	private PasswordEncoder bcryptEncoder;
	
	@Autowired
	private Helper helper;
	
	@RequestMapping(value = "/me", method = RequestMethod.GET)
	public ResponseEntity<?> me() throws Exception {
        UserDetails currentUser = (UserDetails)SecurityContextHolder.getContext().getAuthentication().getPrincipal();
		return ResponseEntity.ok(convertToDto(helper.getUserToken(currentUser)));
	}
	
	@RequestMapping(value = "/user/{id}", method = RequestMethod.PUT)
	public ResponseEntity<?> putUser(@RequestBody UserDTO userDto, @PathVariable Long id) {
		User user = convertToEntity(userDto, id);
		if (user == null) {
			return new ResponseEntity(HttpStatus.NOT_FOUND);
		}
        User tokenUser = helper.getUserToken((UserDetails)SecurityContextHolder.getContext().getAuthentication().getPrincipal());
		if (user.getId() != tokenUser.getId()) {
			return new ResponseEntity(HttpStatus.UNAUTHORIZED);
		}
		return ResponseEntity.ok(convertToDto(userService.updateUser(user)));
	}

	@RequestMapping(value = "/user/{id}/password", method = RequestMethod.PUT)
	public ResponseEntity<?> putPasswordUser(@RequestBody UserPasswordDTO userDTO, @PathVariable Long id) {
        User tokenUser = helper.getUserToken((UserDetails)SecurityContextHolder.getContext().getAuthentication().getPrincipal());
		if (id != tokenUser.getId()) {
			return new ResponseEntity(HttpStatus.UNAUTHORIZED);
		}
		if (!bcryptEncoder.matches(userDTO.getOldPassword(), tokenUser.getPassword())) {
			return new ResponseEntity(HttpStatus.NOT_ACCEPTABLE);
		}
		userService.updatePasswordUser(tokenUser, userDTO.getNewPassword());
		return new ResponseEntity(HttpStatus.OK);
	}

	@RequestMapping(value = "/user/email/{email}/{id}", method = RequestMethod.GET)
	public ResponseEntity<?> nameExit(@PathVariable String email, @PathVariable Long id) throws Exception {
		User user = userService.getUserByEmail(email);
		if (user == null) {
			return new ResponseEntity(HttpStatus.NOT_FOUND);
		}
		Member member = memberService.getMemberByOrganizationIdAndByUser(id, user);
		if (member != null) {
			return new ResponseEntity(HttpStatus.CONFLICT);
		}
		return ResponseEntity.ok(convertToDto(user));
	}

	@RequestMapping(value = "/user/organization", method = RequestMethod.GET)
	public ResponseEntity<?> getUserOrganization() throws Exception {
        User user = helper.getUserToken((UserDetails)SecurityContextHolder.getContext().getAuthentication().getPrincipal());
        
        List<OrganizationDTO> responseOrganization = new ArrayList<OrganizationDTO>();
        for (Organization organization : organizationService.getOrganizationOwnered(user)) 
        { 
        	responseOrganization.add(OrganizationToDTO(organization));
        }
		return ResponseEntity.ok(responseOrganization);
	}
	
	private UserDTO convertToDto(User user) {
		UserDTO userDto = modelMapper.map(user, UserDTO.class);
		return userDto;
	}
	
	private User convertToEntity(UserDTO userDto, long id) {
		User user = userService.getUserById(id);
		if (user == null) {
			return null;
		}
		user.setFirstName(userDto.getFirstName());
		user.setLastName(userDto.getLastName());
		return user;
	}

	private OrganizationDTO OrganizationToDTO(Organization organization) {
		return new OrganizationDTO(
        				organization.getId(),
        				organization.getName()
        		);
	}
}
